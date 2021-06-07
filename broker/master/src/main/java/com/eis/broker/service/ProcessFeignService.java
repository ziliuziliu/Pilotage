package com.eis.broker.service;

import com.eis.broker.entity.OrderLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url = "broker-process-service:8080", value = "broker-process-service")
public interface ProcessFeignService {

    @RequestMapping(value = "/process/order/process")
    boolean process(@RequestBody OrderLog orderLog);

}
