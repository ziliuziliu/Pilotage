package com.eis.gateway.controller;

import com.eis.common.util.Msg;
import com.eis.gateway.dto.OrderStatusInfo;
import com.eis.gateway.service.OrderService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/order",method= RequestMethod.POST)
    public Msg<OrderStatusInfo> addOrder(@RequestBody JsonObject jsonObject){
        return orderService.addOrder(jsonObject);
    }
}
