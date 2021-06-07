package com.eis.broker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProcessApplication {

    public static void main(String[] args){
        SpringApplication.run(ProcessApplication.class, args);
    }
}
