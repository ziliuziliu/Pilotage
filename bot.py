import requests
import time
import random
import json


def build_order(product, current_price):
    data = {'orderId': 'bot', 'product': product, 'quantity': random.randint(50, 150), 'company': 'bot co.', 'trader': 'bot'}
    if random.randint(0, 1) == 0:
        data['side'] = 'BUY'
    else:
        data['side'] = 'SELL'
    if random.randint(0, 2) == 0:
        data['type'] = 'MARKET'
    else:
        data['type'] = 'LIMIT'
    data['price'] = 0
    if data['type'] == 'LIMIT':
        level = random.randint(0, 4)
        if data['side'] == 'BUY':
            data['price'] = current_price - (level + 1) * 2
        else:
            data['price'] = current_price + level * 2
    return data


def run(product):
    while True:
        time.sleep(0.5)
        res = requests.get('http://202.120.40.8:30551/process/product/getMarketDepth', params={'product': product})
        res_body = res.json()
        data = build_order(product, res_body['currentPrice'])
        header = {'content-type': 'application/json'}
        print('sending order ', time.time(), data)
        requests.post('http://202.120.40.8:30556/master/order/receive', data=json.dumps(data), headers=header)


run('i2111')
