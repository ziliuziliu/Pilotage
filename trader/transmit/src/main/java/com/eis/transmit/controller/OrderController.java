package com.eis.transmit.controller;

import com.eis.common.util.*;
import com.eis.transmit.dto.OrderStatusInfo;
import com.eis.transmit.service.OrderService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order",method = RequestMethod.PUT)
    public Msg<OrderStatusInfo> addOrder(@RequestBody JsonObject jsonObject){
        if(!jsonObject.has("product")||!jsonObject.has("side")||
        !jsonObject.has("type")||!jsonObject.has("trader")){
            throw new RuntimeException(MsgUtil.PARAM_DEFICIT_MSG);
        }
        String product=jsonObject.get("product").getAsString();
        UserSide side=UserSide.valueOf(jsonObject.get("side").getAsString());
        String trader=jsonObject.get("trader").getAsString();
        OrderType orderType=OrderType.valueOf(jsonObject.get("type").getAsString());
        if(orderType==OrderType.LIMIT||orderType==OrderType.STOP){
            if(!jsonObject.has("price")||!jsonObject.has("quantity"))throw new RuntimeException(MsgUtil.PARAM_DEFICIT_MSG);
            Integer quantity=jsonObject.get("quantity").getAsInt();
            Integer price=jsonObject.get("price").getAsInt();
            return new Msg<>(MsgCode.SUCCESS,orderService.addOrder(product,quantity,price,side,orderType,trader));
        }
        if(orderType==OrderType.MARKET){
            if(!jsonObject.has("quantity"))throw new RuntimeException(MsgUtil.PARAM_DEFICIT_MSG);
            Integer quantity=jsonObject.get("quantity").getAsInt();
            return new Msg<>(MsgCode.SUCCESS,orderService.addOrder(product,quantity,0,side,orderType,trader));
        }
        return new Msg<>(MsgCode.SUCCESS,orderService.addOrder(product,0,0,side,orderType,trader));
    }
}
