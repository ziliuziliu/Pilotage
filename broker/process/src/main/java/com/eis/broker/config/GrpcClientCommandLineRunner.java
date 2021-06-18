package com.eis.broker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientCommandLineRunner implements CommandLineRunner {

    private final GrpcClientConfiguration configuration;

    @Autowired
    public GrpcClientCommandLineRunner(GrpcClientConfiguration grpcClientConfiguration) {
        this.configuration = grpcClientConfiguration;
    }

    @Override
    public void run(String... args) {
        configuration.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                configuration.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}
