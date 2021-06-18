<template>
    <div>
        <my-header></my-header>
        <div class="menu">
            <my-menu></my-menu>
            <el-form  class="form1" :model="newOrderForm">
                <h2>Make Order</h2>

                <input type="text" placeholder="Product" v-model="newOrderForm.product"/>
                <label>Product</label>
                <input type="text" placeholder="Broker" v-model="newOrderForm.quantity"/>
                <label>Quantity</label>
                <input type="text" placeholder="Price" v-model="newOrderForm.price"/>
                <label>Price</label>
                <br>
                <label>
                    <select id="selects" v-model="newOrderForm.side">
                        <option disabled value="">Choose the side</option>
                        <option>SELL</option>
                        <option>BUY</option>
                    </select>
                </label>
                <br>
                <label>Side</label>
               <br>
                <label >
                    <select id="selects" v-model="newOrderForm.type">
                        <option disabled value="">Choose the type</option>
                        <option>MARKET</option>
                        <option>LIMIT</option>
                        <option>STOP</option>
                        <option>CANCEL</option>
                    </select>
                </label>
                <br>
                <label>Type</label>
                <input type="text" placeholder="Trader" v-model="newOrderForm.trader"/>
                <label>Trader</label>
                <!--                </div>-->
                <div class="button">
                    <div class="set_7_btn-wrapper">
                        <svg height="45" width="150">
                            <rect id="set_7_button4" height="45" width="150"></rect>
                        </svg>
                        <div id="set_7_text"><a href="" @click="handleRequest()">Make Order</a></div>
                    </div>
                </div>
            </el-form>
            <el-form id="result" class="form2">
                <div v-if="handled">
                <div>
                OrderId: {{newOrderResult.orderId}}
                </div>
                <div >
                 Order Status: {{newOrderResult.orderStatus}}
                </div>
                </div>
            </el-form>
        </div>
        <my-footer></my-footer>
    </div>
</template>

<script>
    import Header from '../common/Header'
    import Menu from '../common/Menu'
    import Footer from '../common/Footer'
    import { requestMakeNewOrder } from '../api/request'

    export default {
        name: "MakeOrder",
        components: {
            'my-header':Header,
            'my-menu':Menu,
            'my-footer':Footer
        },
        data(){
            return {
                newOrderForm: {
                    product: '',
                    quantity: '',
                    price:'',
                    side: '',
                    type: '',
                    trader:''
                },
                newOrderResult:{
                    orderId: null,
                    orderStatus: "",
                },
                handled: false
            }
        },
        methods: {
            handleRequest(){
                let product = this.newOrderForm.product;
                let quantity = this.newOrderForm.quantity;
                let price = this.newOrderForm.price;
                let side = this.newOrderForm.side;
                let type = this.newOrderForm.type;
                let trader = this.newOrderForm.trader;
                requestMakeNewOrder(product, quantity,price,side,type, trader).then((res) => {
                    console.log(res);
                    if (res.status === 200 && res.msg === "success") {
                        this.handled = true;
                        this.newOrderResult.orderId = res.data.orderId;
                        this.newOrderResult.orderStatus = res.data.orderStatus;
                    }
                });
            }
        }
    }
</script>

<style>

    * {
        box-sizing: border-box;
    }
    .menu{
        display: flex;
        justify-content: space-between;
    }
  .form1{
        margin: auto;
        margin-left: 150px;
        color: #66fcf1;
    }
    .form2{
        margin-top: 50px;
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

#selects{
    background: #012B39;
    color: #66fcf1;
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

    /*       Just setting CSS for the page   */

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
