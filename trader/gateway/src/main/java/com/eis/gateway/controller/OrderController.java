package com.eis.gateway.controller;

import com.eis.common.util.Msg;
import com.eis.gateway.dto.OrderInfo;
import com.eis.gateway.dto.OrderStatusInfo;
import com.eis.gateway.service.OrderFeignService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trader")
public class OrderController {
    @Autowired
    private OrderFeignService orderFeignService;

    @RequestMapping(value="/order",method= RequestMethod.POST)
    public Msg<OrderStatusInfo> addOrder(@RequestBody JsonObject jsonObject){
        return orderFeignService.addOrder(jsonObject);
    }

    @RequestMapping(value="/order",method=RequestMethod.GET)
    Msg<List<OrderInfo>> findByUserId(@RequestParam("userId")Integer userId){
        return orderFeignService.findByUserId(userId);
    }

    @RequestMapping(value="/iceberg",method = RequestMethod.POST)
    Msg<Boolean> iceberg(@RequestBody JsonObject jsonObject) {
        return orderFeignService.iceberg(jsonObject);
    }
}
