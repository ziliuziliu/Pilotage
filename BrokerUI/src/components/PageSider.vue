<template>
    <a-layout-sider id="pageheader" v-model="collapsed" collapsible>
        <div class="logo"></div>
        <a-menu theme="dark" mode="inline">
            <a-menu-item key="1" @click="toProduct">
                <span style="font-size: larger; font-weight: bolder">Product</span>
            </a-menu-item>
            <a-menu-item key="2" @click="toBlotter">
                <span style="font-size: larger; font-weight: bolder">Blotter</span>
            </a-menu-item>
        </a-menu>
    </a-layout-sider>
</template>

<script>
    import Vue from 'vue';
    import Antd from 'ant-design-vue';
    import 'ant-design-vue/dist/antd.css';
    import '../css/PageSider.css'

    Vue.config.productionTip = false;
    Vue.use(Antd);
    export default {
        name: "PageSider",
        data() {
            return {
                collapsed: false,
            };
        },
        methods: {
            toProduct() {
                this.$router.push("/product")
            },
            toBlotter() {
                this.$router.push("/blotter")
            }
        }
    }
</script>

<style scoped>

</style>
