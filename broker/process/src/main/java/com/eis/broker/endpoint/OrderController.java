package com.eis.broker.endpoint;

import com.eis.broker.entity.OrderLog;
import com.eis.broker.orderbook.Order;
import com.eis.broker.service.OrderBookService;
import com.eis.broker.service.OrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderLogService orderLogService;
    private OrderBookService orderBookService;

    @Autowired
    public OrderController(OrderLogService orderLogService, OrderBookService orderBookService) {
        this.orderLogService = orderLogService;
        this.orderBookService = orderBookService;
    }

    @RequestMapping("/process")
    public boolean process(@RequestBody OrderLog orderLog) {
        orderLogService.saveOrderLog(orderLog);
        orderBookService.process(orderLog.getProduct(), orderLog.getType(), new Order(orderLog));
        return true;
    }



}
