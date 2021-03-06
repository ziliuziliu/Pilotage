package com.eis.transmit.controller;

import com.eis.common.util.*;
import com.eis.transmit.dto.OrderStatusInfo;
import com.eis.transmit.entity.Order;
import com.eis.transmit.service.OrderService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public Msg<OrderStatusInfo> addOrder(@RequestBody JsonObject jsonObject){
        if(!jsonObject.has("product")|| !jsonObject.has("type")) {
            throw new RuntimeException(MsgUtil.PARAM_DEFICIT_MSG);
        }
        String product=jsonObject.get("product").getAsString();
        OrderType orderType=OrderType.valueOf(jsonObject.get("type").getAsString());
        if(orderType==OrderType.LIMIT||orderType==OrderType.STOP){
            UserSide side=UserSide.valueOf(jsonObject.get("side").getAsString());
            Integer userId=jsonObject.get("userId").getAsInt();
            if(!jsonObject.has("price")||!jsonObject.has("quantity"))throw new RuntimeException(MsgUtil.PARAM_DEFICIT_MSG);
            Integer quantity=jsonObject.get("quantity").getAsInt();
            Integer price=jsonObject.get("price").getAsInt();
            return new Msg<>(MsgCode.SUCCESS,orderService.addOrder(product,quantity,price,side,orderType,userId));
        }
        else if (orderType==OrderType.MARKET){
            UserSide side=UserSide.valueOf(jsonObject.get("side").getAsString());
            Integer userId=jsonObject.get("userId").getAsInt();
            if(!jsonObject.has("quantity"))throw new RuntimeException(MsgUtil.PARAM_DEFICIT_MSG);
            Integer quantity=jsonObject.get("quantity").getAsInt();
            return new Msg<>(MsgCode.SUCCESS,orderService.addOrder(product,quantity,0,side,orderType,userId));
        }
        else {
            String orderId = jsonObject.get("orderId").getAsString();
            return new Msg<>(MsgCode.SUCCESS, orderService.cancelOrder(product, orderType, orderId));
        }
    }

    @RequestMapping(value="/order",method=RequestMethod.GET)
    public Msg<List<Order>> findByUserId(@RequestParam("userId")Integer userId){
        return new Msg<>(MsgCode.SUCCESS,orderService.findAllByUserId(userId));
    }

    @RequestMapping(value="/iceberg",method=RequestMethod.POST)
    public Msg<Boolean> iceberg(@RequestBody JsonObject jsonObject){
        if(!jsonObject.has("product")||!jsonObject.has("side")||
                !jsonObject.has("quantity")||!jsonObject.has("userId")){
            throw new RuntimeException(MsgUtil.PARAM_DEFICIT_MSG);
        }
        String product=jsonObject.get("product").getAsString();
        UserSide side=UserSide.valueOf(jsonObject.get("side").getAsString());
        Integer userId=jsonObject.get("userId").getAsInt();
        Integer quantity=jsonObject.get("quantity").getAsInt();
        return new Msg<>(MsgCode.SUCCESS,orderService.batch(product,quantity,side,userId));
    }

}
