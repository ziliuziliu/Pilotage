<template>
    <div class="root" :style="image">
        <my-header></my-header>
        <div class="menu">
            <my-menu></my-menu>
            <div id="welcome">
                 Welcome to our trading system
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
        name: 'Main',
        components: {
            'my-header':Header,
            'my-menu':Menu,
            'my-footer':Footer
        },

    }
</script>

<style>
    #welcome{
        font-size: 40px;
        color: #66FCF1;
        margin: 2em 5em 2em 3em;
    }
    .menu{
        display: flex;
        justify-content: space-between;
        /* margin: 4em 2em; */
    }
</style>
