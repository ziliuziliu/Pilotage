package com.eis.broker.endpoint;

import com.eis.broker.entity.OrderLog;
import com.eis.broker.entity.OrderQueue;
import com.eis.broker.service.OrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/master/order")
public class OrderController {

    private final OrderQueue orderQueue;
    private final OrderLogService orderLogService;

    @Autowired
    public OrderController(OrderQueue orderQueue, OrderLogService orderLogService) {
        this.orderQueue = orderQueue;
        this.orderLogService = orderLogService;
    }

    @RequestMapping("/receive")
    public boolean receive(@RequestBody OrderLog orderLog) {
        orderLogService.saveOrderLog(orderLog);
        orderQueue.addToQueue(orderLog);
        return true;
    }

    @RequestMapping("/available")
    public boolean available(@RequestParam String product) {
        orderQueue.productAvailable(product);
        return true;
    }

    @RequestMapping("/UUID")
    public String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
