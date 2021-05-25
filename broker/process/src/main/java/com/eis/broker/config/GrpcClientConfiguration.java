package com.eis.broker.config;

import com.eis.broker.endpoint.DispatchGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class GrpcClientConfiguration {

    @Value("${broker.master.name}")
    private String host;

    @Value("${broker.master.grpc.port}")
    private int port;

    private ManagedChannel channel;
    private DispatchGrpc.DispatchBlockingStub stub;

    public void start() {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        stub = DispatchGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
    }

    public DispatchGrpc.DispatchBlockingStub getStub() {
        return this.stub;
    }

}
