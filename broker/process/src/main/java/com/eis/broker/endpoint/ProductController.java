package com.eis.broker.endpoint;

import com.eis.broker.entity.ProductData;
import com.eis.broker.message.MarketDepthMsg;
import com.eis.broker.orderbook.OrderBook;
import com.eis.broker.service.OrderBookService;
import com.eis.broker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process/product")
public class ProductController {

    private final ProductService productService;
    private final OrderBookService orderBookService;

    @Autowired
    public ProductController(ProductService productService, OrderBookService orderBookService) {
        this.productService = productService;
        this.orderBookService = orderBookService;
    }

    @RequestMapping("/add")
    public ProductData add(@RequestBody ProductData productData) {
        return productService.add(productData);
    }

    @RequestMapping("/initOrderBook")
    public OrderBook initOrderBook(@RequestParam String product) {
        return orderBookService.initOrderBook(product);
    }

    @RequestMapping("/getMarketDepth")
    public MarketDepthMsg getMarketDepth(@RequestParam String product) {
        return orderBookService.getMarketDepth(product);
    }
}
