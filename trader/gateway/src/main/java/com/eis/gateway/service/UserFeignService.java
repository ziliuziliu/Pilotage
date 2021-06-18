package com.eis.gateway.service;

import com.eis.common.util.Msg;
import com.eis.gateway.dto.OrderStatusInfo;
import com.eis.gateway.dto.UserInfo;
import com.google.gson.JsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url="${trader-user-service}",value = "trader-user-service")
public interface UserFeignService {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    Msg<UserInfo> login(@RequestBody JsonObject jsonObject);
}
