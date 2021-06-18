package com.eis.broker.config;

import com.eis.broker.endpoint.DispatchGrpcService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcServerConfiguration {

    private final DispatchGrpcService dispatchGrpcService;

    @Autowired
    public GrpcServerConfiguration(DispatchGrpcService dispatchGrpcService) {
        this.dispatchGrpcService = dispatchGrpcService;
    }

    @Value("${broker.master.grpc.port}")
    private int port;
    private Server server;

    public void start() throws IOException {
        server = ServerBuilder.forPort(port).addService(dispatchGrpcService).build().start();
        Runtime.getRuntime().addShutdownHook(new Thread(GrpcServerConfiguration.this::stop));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void block() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
