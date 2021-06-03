package com.eis.broker.endpoint;

import com.eis.broker.entity.TransactionData;
import com.eis.broker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/process/transaction")
@CrossOrigin
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping("/findAll")
    public List<TransactionData> findAll() {
        return transactionService.findAll();
    }

}
