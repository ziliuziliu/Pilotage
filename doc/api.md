## API
### 1. Kafka Messages
#### 1.1 OrderStatus (broker -> trader) (topic: "STATUS")
- orderId: String
- status: String (其中ERROR为订单出现错误，其他状态为正常更新，应当被写到trader维护的订单状态中)
- message: String
#### 1.2 Transaction (broker -> trader) (topic: "TRANSACTION")
- tradeId: String
- broker: String
- product: String
- price: Integer
- quantity: Integer
- sellName: String
- sellCompany: Strinig
- buyName: String
- buyCompany: String
- initSide: String (BUY, SELL)
#### 1.3 MarketDepth (broker -> trader) (topic: "MARKET_DEPTH")
- product: String
- broker: String
- currentPrice: Integer
- buyPrice: Integer[] (买1 -> 买5)
- buyAmount: Integer[]
- sellPrice: Integer[] (卖1 -> 卖5)
- sellAmount: Integer[]
#### 1.4 Order (trader -> broker) (topic: "ORDER")
- orderId: String
- product: String
- quantity: Integer
- price: Integer
- side: String (BUY, SELL)
- type: String (MARKET, LIMIT, STOP, CANCEL)
- company: String
- trader: String
- 根据四种订单，可只传一部分字段
