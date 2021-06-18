<template>
    <div>
        <my-header></my-header>
        <div class="menu">
            <my-menu></my-menu>
            <el-form :model="marketForm" class="form1">
                <h2>Market Depth</h2>
                    <input type="text" placeholder="Product" v-model="marketForm.product"/>
                    <label>Product</label>
                <div class="button">
                    <div class="set_7_btn-wrapper">
                        <svg height="45" width="150">
                            <rect id="set_7_button4" height="45" width="150"></rect>
                        </svg>
                        <div id="set_7_text"><a href="" @click="handleRequest()">View Market Depth</a></div>
                    </div>
                </div>
            </el-form>
            <el-form id="result" class="form2">
                <div v-if="handled">
                <div>
                    Product:{{marketDepthResult.product}}
                </div>
                <div >
                    Broker:{{marketDepthResult.broker}}
                </div>
                <div >
                    Current Price:{{marketDepthResult.currentPrice}}
                </div>
                    <div >
                        <div>Buy Price ----- Amount</div>
                        <ul>
                            <li  v-bind:key="item" v-for="(item, index) in marketDepthResult.buyPrice" >
                                buy{{ index + 1}} - {{ item }} ----- {{marketDepthResult.buyAmount[index]}}
                            </li>
                        </ul>
                    </div>
                    <div >
                        <div>Sell Price ----- Amount</div>
                        <ul>
                            <li  v-bind:key="item" v-for="(item, index) in marketDepthResult.sellPrice" >
                                sell{{ index + 1}}  -  {{ item }} ----- {{marketDepthResult.sellAmount[index]}}
                            </li>
                        </ul>
                    </div>
                </div>
            </el-form>
            <div>

            </div>
        </div>
        <my-footer></my-footer>

    </div>
</template>

<script>
    import Header from '../common/Header'
    import Menu from '../common/Menu'
    import Footer from '../common/Footer'
    import { requestMarketDepth } from '../api/request'
    export default {
        name: "MarketDepth",
        components: {
            'my-header':Header,
            'my-menu':Menu,
            'my-footer':Footer
        },
        data(){
            return {
                marketForm: {
                    product: '',
                    broker: ''
                },
                marketDepthResult:{
                    product: "",
                    broker: "",
                    currentPrice: null,
                    buyPrice: [],
                    buyAmount: [],
                    sellPrice: [],
                    sellAmount:[],
                },
                handled : false
            }
    },
        methods: {
            handleRequest(){
                        let product = this.marketForm.product;
                        console.log(product);
                        requestMarketDepth(product).then((res) => {
                            console.log(res);
                            if (res.status === 200 && res.msg === "success") {
                                this.handled= true;
                                this.marketDepthResult.product = res.data.product;
                                this.marketDepthResult.broker = res.data.broker;
                                this.marketDepthResult.currentPrice = res.data.currentPrice;
                                this.marketDepthResult.buyPrice = res.data.buyAmount;
                                this.marketDepthResult.buyAmount = res.data.buyAmount;
                                this.marketDepthResult.sellPrice = res.data.sellAmount;
                                this.marketDepthResult.sellAmount = res.data.sellAmount;
                            }
                        });
            }
        }
    }

</script>

<style scoped>
    * {
        box-sizing: border-box;
    }
    .menu{
        display: flex;
        justify-content: space-between;
        /*margin: 4em 2em;*/
        /*float: left;*/
    }
    .form1{
        margin: auto;
        margin-left: 150px;
        margin-top: 90px;
        color: #66fcf1;
    }
    .form2{
        margin-top: 80px;
        margin-right: 200px;
        color: #66fcf1;
    }

    label {
        position: relative;
        top: -20.6666666667px;
        color: rgba(255, 255, 255, 0.5);
        font: 400 24.6666666667px Roboto;
        cursor: text;
        transition: 0.25s ease;
        margin-top: -15px;
    }
    #result{
        font-size: 20px;
    }
    input {
        display: block;
        width: 80%;
        height: 55%;
        padding-top: 10.6666666667px;
        border: none;
        border-radius: 0;
        color: #66fcf1;
        background: #012B39;
        font-size: 22.6666666667px;
        transition: 0.3s ease;
    }
    input:valid ~ label {
        top: 0;
        font: 700 15px Roboto;
        color: rgba(255, 255, 255, 0.5);
    }
    input:focus {
        outline: none;
    }
    input:focus ~ label {
        top: 0;
        font: 700 10px Roboto;
        color: #66fcf1;
    }
    input:focus ~ .bar:before {
        transform: translateX(0);
    }
    input:-webkit-autofill {
        /* -webkit-box-shadow: 0 0 0px 1000px #333 inset; */
        -webkit-text-fill-color: white !important;
    }

    ::selection {
        background: rgba(33, 150, 243, 0.3);
    }


    @import url(http://fonts.googleapis.com/css?family=Roboto:400,100,900);
    .button {
        width: 100%;
        float: left;
    }

    .set_7_btn-wrapper {
        /* float:left; */
        width: auto;
        line-height: 45px;
        display: inline-block;
        margin-right: 2em;
        text-align: center;
    }

    #set_7_text {
        margin-top: -60px;
        text-align: center;
    }

    #set_7_text a {
        color: #66fcf1;
        text-decoration: none;
        font-weight: 400;
    }

    #set_7_button4 {
        stroke-width: 6px;
        fill: transparent;
        stroke: #66fcf1;
        stroke-dasharray: 85 400;
        stroke-dashoffset: -228;
        transition: 1s all ease;
    }
    .set_7_btn-wrapper:hover #set_7_button4 {
        stroke-dasharray: 50 0;
        stroke-width: 3px;
        stroke-dashoffset: 0;
        stroke: #66fcf1;
    }

</style>
