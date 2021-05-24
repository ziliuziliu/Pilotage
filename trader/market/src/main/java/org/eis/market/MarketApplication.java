package org.eis.market;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketApplication {
    public static Gson gson=new Gson();

    public static final Logger logger = LoggerFactory.getLogger(MarketApplication.class);

    public static void main(String[] args){
        SpringApplication.run(MarketApplication.class, args);
    }
}
