package com.eis.transmit.service;

import com.eis.common.util.Msg;
import com.eis.transmit.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="${trader-user-service}",value = "trader-user-service")
public interface UserFeignService {
    @RequestMapping(value="/user",method = RequestMethod.GET)
    Msg<UserInfo> findByUserId(@RequestParam("userId") Integer userId);
}
