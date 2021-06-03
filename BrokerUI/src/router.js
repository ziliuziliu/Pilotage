import Vue from "vue";
import VueRouter from "vue-router";
import Router from "vue-router";
import MarketView from "./views/MarketView";
import BlotterView from "./views/BlotterView";
import ProductView from "./views/ProductView";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        redirect: '/product'
    },
    {
        path: '/product',
        name: 'product',
        component: ProductView
    },
    {
        path: '/market',
        name: 'market',
        component: MarketView
    },
    {
        path: '/blotter',
        name: 'blotter',
        component: BlotterView
    },
];

var router =  new VueRouter({
    routes
});


Vue.use(Router);



const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
};

export default router;
