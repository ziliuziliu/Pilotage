package com.eis.transmit.serviceImpl;

import com.eis.common.util.OrderStatus;
import com.eis.common.util.OrderType;
import com.eis.common.util.UserSide;
import com.eis.transmit.TransmitApplication;
import com.eis.transmit.dao.OrderDao;
import com.eis.transmit.dto.OrderInfo;
import com.eis.transmit.dto.OrderStatusInfo;
import com.eis.transmit.dto.UserInfo;
import com.eis.transmit.dto.WeightInfo;
import com.eis.transmit.entity.Order;
import com.eis.transmit.service.BroadcastFeignService;
import com.eis.transmit.service.OrderFeignService;
import com.eis.transmit.service.OrderService;
import com.eis.transmit.service.UserFeignService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class OrderServiceImpl implements OrderService {
    private final String TAG="OrderService ";

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private OrderFeignService orderFeignService;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private BroadcastFeignService broadcastFeignService;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Data
    static
    class PendingOrderItem {
        private long timestamp;
        private String product;
        private Integer quantity;
        private Integer price;
        private UserSide side;
        private OrderType type;
        private Integer userId;

        public PendingOrderItem(long timestamp, String product, Integer quantity, Integer price,
                                UserSide side, OrderType type, Integer userId) {
            this.timestamp = timestamp;
            this.product = product;
            this.quantity = quantity;
            this.price = price;
            this.side = side;
            this.type = type;
            this.userId = userId;
        }
    }

    private PriorityQueue<PendingOrderItem> q = new PriorityQueue<>(Comparator.comparingLong(PendingOrderItem::getTimestamp));

    @Override
    public OrderStatusInfo addOrder(String product, Integer quantity, Integer price,
                                    UserSide side, OrderType type,Integer userId) {
        TransmitApplication.logger.info(TAG+"make request for orderId");
        String orderId=orderFeignService.getUUID();
        TransmitApplication.logger.info(TAG+"orderId get");
        UserInfo userInfo =userFeignService.findByUserId(userId).getData();
        if(userInfo==null){
            TransmitApplication.logger.info(TAG+"null user");
            return null;
        }
        System.out.println(userInfo.toString());
        OrderInfo orderInfo=new OrderInfo(orderId,product,quantity,price,side,type,userInfo.getCompany(),userInfo.getUsername());
        kafkaTemplate.send("ORDER",getHashCode(product),product,TransmitApplication.gson.toJson(orderInfo));
        Order order=new Order(orderId,userId,product,quantity,price,side.toString(),type.toString());
        return orderDao.saveOrder(order);
    }

    @Override
    public OrderStatusInfo cancelOrder(String product, OrderType type, String orderId) {
        TransmitApplication.logger.info(TAG+"make request for orderId");
        OrderInfo orderInfo=new OrderInfo(orderId, product, type);
        kafkaTemplate.send("ORDER",getHashCode(product), product, TransmitApplication.gson.toJson(orderInfo));
        return orderDao.updateStatusByOrderId(new OrderStatusInfo(orderId, OrderStatus.CANCELING));
    }

    @Scheduled(fixedRate = 1000 * 5)
    public void sendPendingOrder() {
        long current = System.currentTimeMillis();
        while (q.peek() != null && q.peek().getTimestamp() <= current) {
            logger.info("Send pending order time " + current);
            PendingOrderItem orderItem = q.poll();
            if (orderItem == null) continue;
            addOrder(orderItem.getProduct(), orderItem.getQuantity(), orderItem.getPrice(),
                    orderItem.getSide(), orderItem.getType(), orderItem.getUserId());
        }
    }

    @Override
    public boolean batch(String product, Integer quantity, UserSide side, Integer userId) {
        WeightInfo weightInfo = broadcastFeignService.getWeight(product, 5);
        logger.info(weightInfo.toString());
        Double total = 0.0;
        for (int i=0;i<60;i++) total += weightInfo.getWeight()[i];
        for (int i=0;i<60;i++) {
            double ret = (double)quantity*weightInfo.getWeight()[i]/total;
            long awakeTime = System.currentTimeMillis() + (i + 1) * 1000 * 5;
            logger.info("Order " + i + " Awake time " + awakeTime + " Amount " + (int)ret);
            q.add(new PendingOrderItem(awakeTime, product, (int) ret, 0,
                    side, OrderType.MARKET, userId));
        }
        return true;
    }

    @Override
    public List<Order> findAllByUserId(Integer userId) {
        return orderDao.findAllByUserId(userId);
    }

    private Integer getHashCode(String product){
        return Integer.parseInt(product.substring(product.length()-2));
    }
}
