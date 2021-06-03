<template>
    <div class="login" style="height: 800px">
        <div style="width: 30%;margin: auto;padding-top: 10%">
            <a-form
                id="components-form-demo-normal-login"
                :form="form"
                class="login-form"
                @submit="handleSubmit"
            >
                <a-form-item>
                    <a-input
                        v-decorator="[
          'username',
          { rules: [{ required: true, message: 'Please input your username!' }] },
        ]"
                        placeholder="Username"
                    >
                        <a-icon slot="prefix" type="user" style="color: rgba(0,0,0,.25)"/>
                    </a-input>
                </a-form-item>
                <a-form-item>
                    <a-input
                        v-decorator="['password',{ rules: [{ required: true, message: 'Please input your Password!' }] },]"
                        type="password"
                        placeholder="Password"
                    >
                        <a-icon slot="prefix" type="lock" style="color: rgba(0,0,0,.25)"/>
                    </a-input>
                </a-form-item>
                <a-form-item>
                    <a-button type="primary" html-type="submit" class="login-form-button">
                        Log in
                    </a-button>
                </a-form-item>
            </a-form>
        </div>
    </div>


</template>

<script>
import {axiosPost} from "@/util/Ajax";

export default {
    name: 'login',
    beforeCreate() {
        this.form = this.$form.createForm(this, {name: 'normal_login'});
    },
    methods: {
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFields((err, values) => {
                if (!err) {
                    console.log('Login received values of form: ', values);
                    let self = this;
                    const callback = (data) => {
                        console.log(data.data)
                        localStorage.setItem("userId", data.data.userId);
                        self.$message({
                            message: data.msg,
                            type: "success",
                        });
                        self.$router.replace("/");
                    }
                    axiosPost("/User/loginOrRegisterBusiness", values, callback);
                }
            });
        },
    },
};
</script>
<style>
#components-form-demo-normal-login .login-form {
    max-width: 300px;
}

#components-form-demo-normal-login .login-form-forgot {
    float: right;
}

#components-form-demo-normal-login .login-form-button {
    width: 100%;
}

.login {
    background-image: url("../assets/bglogin.jpg"); /*url("src/imgs/bglogin.jpg");*/
    background-attachment: fixed;
    background-repeat: no-repeat;
    background-size: cover;
}
</style>