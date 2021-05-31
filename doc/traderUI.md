### API

返回格式：{msg:String,status:Integer,data:json}

如{msg:success,status:200,data:{test:1}}

1. 登陆: /trader/login

   method:POST

   content-type:application/json

   body:

   company string

   username string

   password string

   return data:

   userId Integer

   company string

   username string

   token string

   

2. 下订单 /trader/order

   method:POST

   content-type:application/json

   body:

   + Integer userId

   - product: String
   - quantity: Integer
   - price: Integer
   - side: String (BUY, SELL)
   - type: String (MARKET, LIMIT, STOP, CANCEL)
   - 根据四种订单，可只传一部分字段

   return data:

   ​	orderId String

   ​	status String 表示订单状态

3. 获取订单 /trader/order

   method:GET

   content-type:application/x-www-form-urlencoded

   Query:

   userId integer

   return data:

   ​	List[	

   ​		orderId String

   ​		status String

   ​		product: String

   ​		quantity: Integer

   ​		price: Integer

   ​		side: String (BUY, SELL)

   ​		type: String (MARKET, LIMIT, STOP, CANCEL)

   ​	]

4. 获取成交记录: /trader/transaction

   method:GET

   content-type:application/x-www-form-urlencoded

   Query:

   userId integer

   return data:

   ​	List[

   ​	tradeId String

   ​	orderId String

   ​	broker String

   ​	product String

   ​	price Integer

   ​	quantity Integer

   ​	sellName String

   ​	sellCompany String

   ​	buyName String

   ​	buyCompany String

   ​	initSide String(BUY,SELL)

   ​	]

5. /trader/market

   content-type:websocket

   Query:

   product String 

   broker String

   return data:

   ​	product: String

   ​	broker: String

   ​	currentPrice: Integer

   ​	buyPrice: Integer[] (买1 -> 买5)

   ​	buyAmount: Integer[]

   ​	sellPrice: Integer[] (卖1 -> 卖5)

   ​	sellAmount: Integer[]