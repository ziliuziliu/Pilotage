package com.eis.transmit;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TransmitApplication {
    public static Gson gson=new Gson();

    public static final Logger logger = LoggerFactory.getLogger(TransmitApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TransmitApplication.class, args);
    }
}
