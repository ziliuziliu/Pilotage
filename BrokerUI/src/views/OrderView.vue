<template>
    <a-layout id="components-layout-demo-side" style="min-height: 100vh">
        <PageSider/>
        <a-layout>
            <PageHeader/>
            <OrderList/>
        </a-layout>
    </a-layout>
</template>
<script>
    // import Vue from 'vue';
    import 'ant-design-vue/dist/antd.css';
    import PageSider from "../components/PageSider";
    import PageHeader from "../components/PageHeader"
    import OrderList from "../components/OrderList";

    // Vue.config.productionTip = false;
    export default {
        components: {
            'PageSider': PageSider,
            'PageHeader': PageHeader,
            'OrderList': OrderList,
        },
        name: 'OrderView',
    };
</script>

<style scoped>

</style>
