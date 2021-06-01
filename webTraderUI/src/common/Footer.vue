<template>
    <div>
        <div>
            <div id="join">
                <p id="join-soc">Pilotage 2021</p>
            </div>
        </div>
    </div>
</template>

<script>

export default{}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Quicksand:wght@500&display=swap');
*{
    padding: 0;
    margin: 0;
    font-family: 'Quicksand', sans-serif;
    color: #66FCF1;
   }
body{
    background: #fff;
}
#location{
    font-size: 30px;
    display: flex;
    justify-content: center;
    padding-bottom: 40px;
}
img{
    color: #66FCF1;
}
#map{
    display: flex;
    justify-content: center;
}
#join{
    /* padding-bottom: 50px; */
    height: 250px;
}
#join-soc{
    display: flex;
    justify-content: center;
    font-size: 25px;
    padding-top: 40px;
}
ul{
    position: relative;
    top: 40%;
    width: 100%;
    text-align: center;
    transform: translateY(-50%);
    white-space: nowrap;
}
ul li{
    list-style: none;
    margin: 0 20px;
    display: inline-block;
    position: relative;
    width: 120px;   height: 120px;
}
ul li a{
    position: relative;
    display: inline-block;
    color: #66FCF1;
    font-size: 40px;
    width: 100%;  height: 100%;
    text-align: center;   line-height: 120px;
}
ul li span{
    display: inline-block;
    position: absolute;
    background: #66FCF1;
    transition: transform .9s;
}
ul li span:nth-of-type(1),ul li span:nth-of-type(2){
    width: 120px;  height: 2px;
}
ul li span:nth-of-type(3),ul li span:nth-of-type(4){
    width: 2px;  height: 120px;
}
ul li span:nth-of-type(1){
    top: 0;  left: 0;
    transform-origin: right;
    transform: scaleX(1);
}
ul li span:nth-of-type(2){
   bottom: 0;   left: 0;
   transform-origin: left;
   transform: scale(1);
}
ul li span:nth-of-type(3){
   top: 0;   left: 0;
   transform-origin: bottom;
   transform: scale(0);
}
ul li span:nth-of-type(4){
   top: 0;  right: 0;
   transform-origin: top;
   transform: scale(0);
}

/*************  On hover  *****************/
ul li:hover span:nth-of-type(1){
    transform-origin: left;
    transform: scaleX(0);
}
ul li:hover span:nth-of-type(2){
   transform-origin: right;
   transform: scale(0);
}
ul li:hover span:nth-of-type(3){
   transform-origin: top;
   transform: scale(1);
}
ul li:hover span:nth-of-type(4){
   top: 0;  right: 0;
   transform-origin: bottom;
   transform: scale(1);
}
</style>
