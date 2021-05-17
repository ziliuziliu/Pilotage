package com.eis.transmit.daoImpl;

import com.eis.common.util.OrderStatus;
import com.eis.transmit.TransmitApplication;
import com.eis.transmit.dao.OrderDao;
import com.eis.transmit.dto.OrderStatusInfo;
import com.eis.transmit.entity.Order;
import com.eis.transmit.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final String TAG="OrderDao";

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderStatusInfo saveOrder(Order order) {
        order.setStatus(OrderStatus.WAITING);
        Objects.requireNonNull(order,TAG+" saveOrder: null order");
        orderRepository.save(order);
        TransmitApplication.logger.info(TAG+ "order save");
        return new OrderStatusInfo(order.getOrderId(),order.getStatus());
    }
}
