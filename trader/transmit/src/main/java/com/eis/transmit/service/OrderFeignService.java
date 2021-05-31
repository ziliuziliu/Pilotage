package com.eis.transmit.service;

import com.eis.common.util.Msg;
import com.google.gson.JsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url="${broker-master-service}")
public interface OrderFeignService {
    @RequestMapping(value = "/master/order/UUID",method = RequestMethod.GET)
    String getUUID();
}
