import Vue from 'vue'
import Router from 'vue-router'
import Main from "./components/Main";
import MakeOrder from "./components/MakeOrder";
import MarketDepth from "./components/MarketDepth";
import CheckOrder from "./components/CheckOrder";
import MyOrders from "./components/MyOrders";

Vue.use(Router);


export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'Main',
            component: Main
        },
        {
            path: '/makeOrder',
            component: MakeOrder
        },
        {
            path: '/marketDepth',
            component: MarketDepth
        },
        {
            path: '/checkOrder',
            component: CheckOrder
        },
        {
            path: '/myOrders',
            component: MyOrders
        },
    ]
})

