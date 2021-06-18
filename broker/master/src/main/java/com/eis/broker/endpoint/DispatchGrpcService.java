package com.eis.broker.endpoint;

import com.eis.broker.entity.OrderQueue;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispatchGrpcService extends DispatchGrpc.DispatchImplBase {

    private final OrderQueue orderQueue;

    @Autowired
    public DispatchGrpcService(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void finished(FinishedRequest request,
                         StreamObserver<FinishedResponse> responseObserver) {
        String product = request.getProduct();
        orderQueue.productAvailable(product);
        FinishedResponse response = FinishedResponse.newBuilder()
                .setStatus(true)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
