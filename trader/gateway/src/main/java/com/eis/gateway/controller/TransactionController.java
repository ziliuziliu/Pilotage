package com.eis.gateway.controller;

import com.eis.common.util.Msg;
import com.eis.gateway.dto.TransactionInfo;
import com.eis.gateway.service.TransactionFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trader")
public class TransactionController {
    @Autowired
    private TransactionFeignService transactionFeignService;

    @RequestMapping(value = "/transaction",method = RequestMethod.GET)
    public Msg<List<TransactionInfo>> findTransactionByUserId(@RequestParam("userId")Integer userId){
        return transactionFeignService.findTransactionByUserId(userId);
    }
}
