package com.eis.transmit.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url="${broker-master-service}",value = "broker-master-service")
public interface OrderFeignService {
    @RequestMapping(value = "/master/order/UUID",method = RequestMethod.GET)
    String getUUID();
}
