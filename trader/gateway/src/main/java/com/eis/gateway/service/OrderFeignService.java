package com.eis.gateway.service;

import com.eis.common.util.Msg;
import com.eis.gateway.dto.OrderStatusInfo;
import com.google.gson.JsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url="${trader-transmit-service}")
public interface OrderFeignService {
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    Msg<OrderStatusInfo> addOrder(@RequestBody JsonObject jsonObject);
}
