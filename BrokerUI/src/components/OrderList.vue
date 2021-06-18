<template>
    <a-layout>
        <span id="commodity-select">
            <a-dropdown>
                <a @click="e => e.preventDefault()">
                    选择商品 <a-icon type="down"/>
                </a>
                <a-menu slot="overlay" @click="onClick">
                    <a-menu-item v-for="(item) in commodityList" :key="item.commodityId">
                        {{item.name}}
                    </a-menu-item>
                </a-menu>
            </a-dropdown>
        </span>
        <span id="order-table">
            <a-table :columns="columns" :data-source="orderList">
            </a-table>
        </span>
    </a-layout>
</template>

<script>
import 'ant-design-vue/dist/antd.css'
import {axiosPost} from '../util/Ajax'

const commodityList = [
    {
        commodityId: '100',
        name: '钢琴',
    },
    {
        commodityId: '200',
        name: '小提琴',
    },
    {
        commodityId: '300',
        name: '长笛',
    }
];
const columns = [
    {
        title: '订单编号',
        dataIndex: 'orderId',
    },
    {
        title: '物品编号',
            dataIndex: 'commodityItemId',
        },
        {
            title: '用户编号',
            dataIndex: 'userId',
        },
        {
            title: '订单阶段',
            dataIndex: 'phase'
        },
        {
            title: '订单状态',
            dataIndex: 'status',
        },
        {
            title: '订单总额',
            dataIndex: 'totalCost'
        }
    ];
    const orderList = [];
    export default {
        name: "OrderList",
        methods: {
            onClick({key}) {
                let param = {commodityId: key};
                console.log(param);
                const callback = (data) => {
                    this.orderList = data.data.data;
                };
                axiosPost("/Order/getOrder", param, callback);
                // let url = 'http://localhost:30333/Order/getOrder';
                // console.log(param);
                // axios.post(url, param)
                //     .then((response) => {
                //         console.log(response.data);
                //         this.orderList = response.data.data;
                //     })
            },
        },
        data() {
            return {
                orderList,
                columns,
                commodityList,
            }
        }
    }
</script>

<style scoped>
    #commodity-select {
        margin-top: 32px;
        margin-left: 32px;
        margin-bottom: 32px;
        width: 100px;
        font-size: larger;
    }

    #order-table {
        margin-left: 32px;
        margin-right: 32px;
    }
</style>
