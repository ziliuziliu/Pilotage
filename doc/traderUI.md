### API(待修改)

1. /trader/order

   method:POST

   content-type:application/json

   body:

   - product: String
   - quantity: Integer
   - price: Integer
   - side: String (BUY, SELL)
   - type: String (MARKET, LIMIT, STOP, CANCEL)
   - company: String
   - trader: String
   - 根据四种订单，可只传一部分字段

2. /trader/market

   method:GET

   content-type:application/x-www-form-urlencoded

   Query:

   product

3. /trader/order

   method:GET

   content-type:application/x-www-form-urlencoded

   Query:

   userId

4. /trader/order/blotter

   method:GET

   content-type:application/x-www-form-urlencoded

   Query:

   userId