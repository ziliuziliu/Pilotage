package com.eis.transaction;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionApplication {
    public static Gson gson=new Gson();

    public static final Logger logger = LoggerFactory.getLogger(TransactionApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }
}
