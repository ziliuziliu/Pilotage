<template>
  <header>
   <div class="logo">
       <router-link to="/" id="router-li">Pilotage</router-link>
    </div>
      <div class="log-reg" v-if="userId!==null">
          <div id="login" class="exit">
              <button type="button" class="btn btn-danger" @click="logOut()">Exit</button>
          </div>
      </div>
   <div class="log-reg" v-else>
       <div id="login">
           <button type="button" class="btn btn-danger" @click="goToLogin()">Log in</button>
       </div>
       <div id="register">
           <button type="button" class="btn btn-success">Register</button>
       </div>
   </div>
  </header>
</template>

<script>

export default {
    methods:{
        goToLogin(){
            this.$router.push('/login');
        },
        logOut(){
            localStorage.clear();
            this.$router.push('/login');
        },
    },
    data(){
        return {
            userId: ""
        }
    },
    created() {
        this.userId=localStorage.getItem('userId');
    }
}
</script>

<style scoped>
header{
    border-bottom: 1px solid #66FCF1;
;
}
header, .log-reg{
    display: flex;
    justify-content: space-between;
    padding: 10px 30px;
}
.logo{
    font-size: 24px;
    color: #66FCF1;
    cursor: pointer;
    padding: 10px 30px;
}
#router-li{
    text-decoration: none;
    color: #66FCF1;
}
.log-reg{
    width: 250px;
    color: #66fcf1;
    font-size: 25px
}
.btn{
    border-radius: 30px;
    background-color: rgb(1, 1, 37);
    color: #66FCF1;
}
.exit{
    margin-left: 180px
}
</style>
