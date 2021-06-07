package com.eis.broker.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "broker-master-service:8080", value = "broker-master-service")
public interface MasterFeignClient {

    @RequestMapping("/master/order/available")
    boolean available(@RequestParam String product);

}
