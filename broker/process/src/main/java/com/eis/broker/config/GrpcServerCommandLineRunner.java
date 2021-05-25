package com.eis.broker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerCommandLineRunner implements CommandLineRunner {

    private final GrpcServerConfiguration configuration;

    @Autowired
    public GrpcServerCommandLineRunner(GrpcServerConfiguration grpcServerConfiguration) {
        this.configuration = grpcServerConfiguration;
    }

    @Override
    public void run(String... args) throws Exception {
        configuration.start();
        configuration.block();
    }
}
