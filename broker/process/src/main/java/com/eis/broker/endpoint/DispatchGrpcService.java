package com.eis.broker.endpoint;

import com.eis.broker.orderbook.Order;
import com.eis.broker.service.OrderBookService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispatchGrpcService extends DispatchGrpc.DispatchImplBase {

    private final OrderBookService orderBookService;

    @Autowired
    public DispatchGrpcService(OrderBookService orderBookService) {
        this.orderBookService = orderBookService;
    }

    @Override
    public void dispatch(DispatchRequest request,
                         StreamObserver<DispatchResponse> responseObserver) {
        String orderId = request.getOrderId();
        String product = request.getProduct();
        Integer quantity = request.getQuantity();
        Integer price = request.getPrice();
        String side = request.getSide();
        String type = request.getType();
        String company = request.getCompany();
        String trader = request.getTrader();
        orderBookService.process(product, type, new Order(orderId, price, quantity, trader, company, side, type));
        DispatchResponse response = DispatchResponse.newBuilder()
                .setStatus(true)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
