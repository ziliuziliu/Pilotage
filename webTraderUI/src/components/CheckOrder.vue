<template>

    <div>
        <my-header></my-header>
        <div class="menu">
        <my-menu></my-menu>
            <div class="form">
                <h2>Check Orders Status</h2>
                <input type="text" placeholder="Your Id"/>
                <label>User ID</label>
                        <div class="button">
                            <div class="set_7_btn-wrapper">
                                <svg height="45" width="150">
                                    <rect id="set_7_button4" height="45" width="150"></rect>
                                </svg>
                                <div id="set_7_text"><a href="">Check</a></div>
                            </div>
                        </div>
            </div>

        </div>
        <my-footer></my-footer>
    </div>
</template>
<script>

    import Header from '../common/Header'
    import Menu from '../common/Menu'
    import Footer from '../common/Footer'

    export default {
        name: 'CheckOrder',
        components: {
            'my-header':Header,
            'my-menu':Menu,
            'my-footer':Footer
        },
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
    .form{
        margin: auto;
        margin-left: 150px;
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
