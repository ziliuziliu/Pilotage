package com.eis.gateway.service;

import com.eis.common.util.Msg;
import com.eis.gateway.dto.OrderStatusInfo;
import com.google.gson.JsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url="${transmit-service-url}",name="transmit")
public interface OrderService {
    @RequestMapping(value = "/order",method = RequestMethod.PUT)
    Msg<OrderStatusInfo> addOrder(@RequestBody JsonObject jsonObject);
}
