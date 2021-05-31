<template>
<div class="out">
    <my-header></my-header>
    <div id="login-pos">
        <Form id="login" :model="loginForm">
            <h1 id="topic">Login</h1>
            <div id="company">
                <FormItem>
                <input type="text" v-model="loginForm.company" />
                <label>Company</label>
                </FormItem>
            </div>
            <div id="username">
                <FormItem>
                <input type="text" v-model="loginForm.username" />
                <label>Your name</label>
                </FormItem>
            </div>
            <div id="login-password">
                <FormItem>
                <div class="field has-addons">
                    <div class="control is-expanded">
                        <input v-if="showPassword" type="text" class="input" v-model="loginForm.password" />
                        <input v-else type="password" class="input" v-model="loginForm.password">
                        <label>Password</label>
                    </div>
                </div>
                </FormItem>
            </div>
            <div class="button">
                <div class="set_7_btn-wrapper">
                    <svg height="45" width="150">
                        <rect id="set_7_button4" height="45" width="150"></rect>
                    </svg>
                    <div id="set_7_text"><a href="" @click="handleRequest()">Login</a></div>
                </div>
            </div>
        </Form>

    </div>
</div>
</template>

<script>
    import Header from '../common/Header'

    import { requestLogin } from '../api/request'

    export default {
        name: 'Login',
        components: {
            'my-header': Header
        },
        data() {
            return {
                showPassword: false,
                token: "",
                userId: null,
                loginForm:{
                    company:"",
                    username:"",
                    password:""
                }
            };
        },
        computed: {
            buttonLabel() {
                return (this.showPassword) ? "Hide" : "Show";
            }
        },
        methods: {
            toggleShow() {
                this.showPassword = !this.showPassword;
            },
            handleRequest(){
                let company = this.loginForm.company;
                let username = this.loginForm.username;
                let password = this.loginForm.password;

                requestLogin(company, username, password).then((res) => {
                    console.log(res);
                    if (res.status === 200 && res.msg === "success") {
                        this.GLOBAL.token=res.data.token;
                        localStorage.setItem('userId', res.data.userId);
                        localStorage.setItem('token',res.data.token);
                        this.$router.push('/main');
                    }
                });

            }
        }
    };
</script>

<style scoped>
    .out{
        width: auto;
        height: 603px;
        height: 100vh;
        color:#66fcf1;
    }
    #login-pos {
        display: flex;
        justify-content: center;
        margin-top: 100px;
    }
    .field {
        display: flex;
        justify-content: center;
    }
    #topic {
        padding-left: 70px;
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
        width: 100%;
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
        -webkit-text-fill-color: white !important;
    }
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
