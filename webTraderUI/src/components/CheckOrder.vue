<template>
    <div>
        <my-header></my-header>
        <div class="menu">
        <my-menu></my-menu>
            <div id="table-orders">
                <h2>Transaction Records</h2>
                <table id="secondTable">
                    <thead>
                    <tr>
                        <th v-bind:key="col" v-for="col in columns">{{col}}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-bind:key="row" v-for="row in transactions">
                        <td v-bind:key="col" v-for="col in columns">{{row[col]}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <my-footer></my-footer>
    </div>
</template>
<script>

    import Header from '../common/Header'
    import Menu from '../common/Menu'
    import Footer from '../common/Footer'
    import {requestTransactions} from "../api/request";

    export default {
        name: 'CheckOrder',
        components: {
            'my-header':Header,
            'my-menu':Menu,
            'my-footer':Footer
        },
        data(){
            return {
                transactions : [],
                columns:["traderId","orderId", "broker", "product",  "price", "quantity","sellName", "sellCompany", "buyName", "buyCompany", "initSide"]
            }
        },
        mounted() {
            let userId = localStorage.getItem('userId');
            this.getTransactions(userId);
        },
        methods:{
            getTransactions(userid){
                let formData = new FormData();

                formData.append('userId', userid);
                requestTransactions(formData).then((res) => {
                    console.log(res);
                    if (res.status === 200 && res.msg === "success") {
                        this.transactions = res.data;
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
    #table-orders{
        font-size: 10px;
    }
    h2{
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

    input {
        display: block;
        width: 80%;
        height: 55%;
        padding-top: 10.6666666667px;
        border: none;
        border-radius: 0;
        color: #66fcf1;
        background: #012b39;
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
        -webkit-text-fill-color: white !important;
    }


    ::selection {
        background: rgba(33, 150, 243, 0.3);
    }

    #set_7_text a {
        color: #66fcf1;
        text-decoration: none;
        font-weight: 400;
    }

    table {

        font-family: 'Open Sans', sans-serif;
        width: 750px;
        border-collapse: collapse;
        border: 3px solid black;

        color: #66FCF1;
    }
    table th {
        text-transform: uppercase;
        text-align: left;
        background: black;
        color: #66fcf1;
        padding: 8px;
        min-width: 30px;
    }
    table td {
        text-align: left;
        padding: 8px;
        border-right: 2px solid black;
    }
    table td:last-child {
        border-right: none;
    }
    table tbody tr:nth-child(2n) td {
        background: #105469;
    }
    table tbody tr:nth-child(2n-1) td {
        background: #012B39
    }
</style>
