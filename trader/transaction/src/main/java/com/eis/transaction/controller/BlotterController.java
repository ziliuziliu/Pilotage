package com.eis.transaction.controller;

import com.eis.common.util.Msg;
import com.eis.common.util.MsgCode;
import com.eis.transaction.entity.Blotter;
import com.eis.transaction.service.BlotterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlotterController {
    @Autowired
    private BlotterService blotterService;

    @RequestMapping(value="/transaction",method = RequestMethod.GET)
    public Msg<List<Blotter>> findTransactionByUserId(@RequestParam("userId")Integer userId){
        return new Msg<>(MsgCode.SUCCESS,blotterService.findByUserId(userId));
    }
}
