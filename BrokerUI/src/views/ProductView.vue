<template>
    <a-layout id="components-layout-demo-side" style="min-height: 100vh">
        <PageSider/>
        <a-layout>
            <PageHeader/>
            <span style="margin-left: 32px; margin-right: 32px; margin-top: 32px">
                <a-table :columns="columns" :data-source="products">
                    <template slot="productName" slot-scope="text">
                        <a-button type="primary" @click="toMarket(text)">View Market Depth</a-button>
                    </template>
                </a-table>
            </span>
        </a-layout>
    </a-layout>
</template>

<script>
    import 'ant-design-vue/dist/antd.css';
    import PageSider from "../components/PageSider";
    import PageHeader from "../components/PageHeader"
    import {axiosPost} from "../util/Ajax";

    const columns = [
        {
            title: 'ID',
            dataIndex: 'productId',
        },
        {
            title: 'NAME',
            dataIndex: 'productName',
        },
        {
            title: 'INFO',
            dataIndex: 'productInfo',
        },
        {
            title: 'Market Depth',
            dataIndex: 'productName',
            key: 'productName',
            scopedSlots: {customRender: 'productName'},
        }
    ];

    export default {
        components: {
            'PageSider': PageSider,
            'PageHeader': PageHeader,
        },
        name: "ProductView",
        data() {
            return {
                columns,
                products: [],
            }
        },
        mounted() {
            let self = this;
            function callback(response) {
                self.products = response;
            }
            axiosPost(30551, '/process/product/findAll', {}, callback);
        },
        methods: {
            toMarket(product)
            {
                this.$router.push({
                    name: 'market',
                    params: {product: product}
                })
            }
        }
    }
</script>

<style scoped>

</style>
