package com.eis.transmit.serviceImpl;

import com.eis.common.util.OrderStatus;
import com.eis.common.util.OrderType;
import com.eis.common.util.UserSide;
import com.eis.transmit.TransmitApplication;
import com.eis.transmit.dao.OrderDao;
import com.eis.transmit.dto.OrderInfo;
import com.eis.transmit.dto.OrderStatusInfo;
import com.eis.transmit.dto.UserInfo;
import com.eis.transmit.entity.Order;
import com.eis.transmit.service.OrderFeignService;
import com.eis.transmit.service.OrderService;
import com.eis.transmit.service.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Order> findAllByUserId(Integer userId) {
        return orderDao.findAllByUserId(userId);
    }

    private Integer getHashCode(String product){
        return Integer.parseInt(product.substring(product.length()-2));
    }
}
