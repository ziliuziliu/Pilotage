package com.eis.gateway.service;

import com.eis.common.util.Msg;
import com.eis.gateway.dto.OrderInfo;
import com.eis.gateway.dto.OrderStatusInfo;
import com.google.gson.JsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url="${trader-transmit-service}",value="trader-transmit-service")
public interface OrderFeignService {
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    Msg<OrderStatusInfo> addOrder(@RequestBody JsonObject jsonObject);

    @RequestMapping(value="/order",method=RequestMethod.GET)
    Msg<List<OrderInfo>> findByUserId(@RequestParam("userId")Integer userId);

    @RequestMapping(value="/iceberg", method = RequestMethod.POST)
    Msg<Boolean> iceberg(@RequestBody JsonObject jsonObject);
}
