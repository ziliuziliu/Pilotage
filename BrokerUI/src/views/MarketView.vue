<template>
    <a-layout id="components-layout-demo-side" style="min-height: 100vh">
        <PageSider/>
        <a-layout>
            <PageHeader/>
            <a-breadcrumb style="margin-top: 36px">
                <a-breadcrumb-item style="font-size: 150%; margin-left: 36px">市场行情</a-breadcrumb-item>
                <a-breadcrumb-item style="font-size: 150%">{{product}}</a-breadcrumb-item>
            </a-breadcrumb>
            <span style="font-size: 150%; margin-left: 36px">现价：{{currentPrice}}</span>
            <a-list item-layout="horizontal" :data-source="sellPrice" style="border:1px solid gray; width: 250px; margin-left: 40%">
                <a-list-item slot="renderItem" slot-scope="item, index">
                    <a-list-item-meta>
                        <a slot="title" style="font-size: 150%; color: red; margin-left: 36px">
                            卖{{5-index}} {{sellPrice[4-index]}} {{sellVolume[4-index]}}
                        </a>
                    </a-list-item-meta>
                </a-list-item>
            </a-list>
            <a-list item-layout="horizontal" :data-source="buyPrice" style="border:1px solid gray; width: 250px; margin-top: 2px; margin-left: 40%">
                <a-list-item slot="renderItem" slot-scope="item, index">
                    <a-list-item-meta>
                        <a slot="title" style="font-size: 150%; color: green; margin-left: 36px">
                            买{{index+1}} {{buyPrice[index]}} {{buyVolume[index]}}
                        </a>
                    </a-list-item-meta>
                </a-list-item>
            </a-list>
        </a-layout>
    </a-layout>
</template>

<script>
    import 'ant-design-vue/dist/antd.css';
    import PageSider from "../components/PageSider";
    import PageHeader from "../components/PageHeader"

    export default {
        components: {
            'PageSider': PageSider,
            'PageHeader': PageHeader,
        },
        name: "MarketView",
        data() {
            return {
                product: '',
                buyPrice: [],
                buyVolume: [],
                sellPrice: [],
                sellVolume: [],
                currentPrice: 0,
                websock: '',
            }
        },
        created() {
            window.addEventListener('beforeunload', e => this.closeWebsocket(e));
        },
        beforeDestroy() {
            window.removeEventListener('beforeunload', e => this.closeWebsocket(e));
        },
        mounted() {
            let product = this.$route.params.product;
            this.product = product;
            console.log(product);
            this.initWebSocket(product);
        },
        methods: {
            initWebSocket(product) {
                console.log(product);
                let wsuri = "ws://localhost:8085/marketDepth/broker/" + product;
                this.websock = new WebSocket(wsuri, "chat");
                this.websock.onmessage = this.onMessage;
                this.websock.onopen = this.onOpen;
                this.websock.onerror = this.onError;
            },
            onOpen() {
            },
            onError() {
                this.initWebSocket();
            },
            onMessage(e) { //数据接收
                let json = JSON.parse(e.data);
                this.buyPrice = json.buyPrice;
                this.buyVolume = json.buyVolume;
                this.sellPrice = json.sellPrice;
                this.sellVolume = json.sellVolume;
                this.currentPrice = json.currentPrice;
            },
            closeWebsocket() {
                if (this.websock)
                    this.websock.close();
            }
        }
    }
</script>

<style scoped>

</style>
