package com.eis.broker.endpoint;

import com.eis.broker.entity.OrderLog;
import com.eis.broker.orderbook.Order;
import com.eis.broker.service.OrderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process/order")
public class OrderController {

    private OrderBookService orderBookService;

    @Autowired
    public OrderController(OrderBookService orderBookService) {
        this.orderBookService = orderBookService;
    }

    @RequestMapping("/process")
    public boolean process(@RequestBody OrderLog orderLog) {
        orderBookService.process(orderLog.getProduct(), orderLog.getType(), new Order(orderLog));
        return true;
    }



}
