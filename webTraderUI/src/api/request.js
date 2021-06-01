import axios from 'axios'

let serverUrl = 'http://localhost:8888';
let traderUrl = serverUrl+'/trader';
export const requestMarketDepth=(product, broker) => {
    axios.get(`${traderUrl}/market`,{
        params:{
            product: product,
            broker: broker
        }
})
};

export const requestMakeNewOrder=(product, quantity,price,side,type, trader) => {
    axios.post(`${traderUrl}/order`,{
        params:{
            product: product,
            quantity: quantity,
            price: price,
            side: side,
            type:type,
            trader: trader
        },
        headers:{
            token: this.GLOBAL.token
        }
    })
};
export const requestMyOrders=(formData) => {
    axios.get(`${traderUrl}/order`, formData, {
        headers:{
            token: this.GLOBAL.token
        }
    })
};
export const requestLogin=(company, username, password) => {
    axios.post(`${traderUrl}/login`,{
        params:{
            company: company,
            username: username,
            password: password
        }
    })
};
