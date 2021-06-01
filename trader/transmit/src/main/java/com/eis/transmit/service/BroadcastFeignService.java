package com.eis.transmit.service;

import com.eis.common.util.Msg;
import com.eis.transmit.dto.UserInfo;
import com.eis.transmit.dto.WeightInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="${broker-broadcast-service}")
public interface BroadcastFeignService {

    @RequestMapping(value="/broadcast/analysis/getWeight",method = RequestMethod.GET)
    WeightInfo getWeight(@RequestParam("product") String product, @RequestParam("hours") Integer hours);

}
