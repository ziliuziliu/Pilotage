### API

返回格式：{msg:String,status:Integer,data:json}

如{msg:success,code:200,data:{test:1}}

1. /trader/order

   method:POST

   content-type:application/json

   body:

   - product: String
   - quantity: Integer
   - price: Integer
   - side: String (BUY, SELL)
   - type: String (MARKET, LIMIT, STOP, CANCEL)
   - trader: String
   - 根据四种订单，可只传一部分字段

   return data:

   ​	orderId String

   ​	status String 表示订单状态

2. /trader/market

   method:GET

   content-type:application/x-www-form-urlencoded

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

3. /trader/order

   method:GET

   content-type:application/x-www-form-urlencoded

   Query:

   userId

   return data:

   ​	List[	

   ​	orderId String

   ​	status String

   ​	]

4. /trader/order/transaction

   method:GET

   content-type:application/x-www-form-urlencoded

   Query:

   userId

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

   ​	
