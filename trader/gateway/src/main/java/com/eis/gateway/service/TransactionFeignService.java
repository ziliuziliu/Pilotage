package com.eis.gateway.service;

import com.eis.common.util.Msg;
import com.eis.gateway.dto.TransactionInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url="${trader-transaction-service}")
public interface TransactionFeignService {
    @RequestMapping(value="/transaction",method = RequestMethod.GET)
    Msg<List<TransactionInfo>> findTransactionByUserId(@RequestParam("userId")Integer userId);
}
