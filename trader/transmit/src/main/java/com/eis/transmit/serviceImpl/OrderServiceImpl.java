package com.eis.transmit.serviceImpl;

import com.eis.common.util.OrderType;
import com.eis.common.util.UserSide;
import com.eis.transmit.TransmitApplication;
import com.eis.transmit.dao.OrderDao;
import com.eis.transmit.dto.OrderStatusInfo;
import com.eis.transmit.entity.Order;
import com.eis.transmit.service.OrderFeignService;
import com.eis.transmit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final String TAG="OrderService";

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private OrderFeignService orderFeignService;

    @Override
    public OrderStatusInfo addOrder(String product, Integer quantity, Integer price,
                                    UserSide side, OrderType type, String trader) {
        TransmitApplication.logger.info(TAG+"make request for orderId");
        String orderId=orderFeignService.getUUID();
        TransmitApplication.logger.info(TAG+"orderId get");
        Order order=new Order(orderId,product,quantity,price,side,type,trader);
        kafkaTemplate.send("ORDER",getHashCode(product),product,TransmitApplication.gson.toJson(order));
        return orderDao.saveOrder(order);
    }

    private Integer getHashCode(String product){
        return Integer.parseInt(product.substring(product.length()-2));
    }
}
