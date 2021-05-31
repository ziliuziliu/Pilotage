<template>
    <div>
        <div id="location">
            <img src="https://img.icons8.com/ios/2x/4a90e2/marker.png" alt="" width="40px">
            <!-- <img src="@/images/location3.png" alt="" height="60px"> -->
            <p>Our Location</p>
        </div>
        <div id="map"><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10378.856605475714!2d121.4381666604976!3d31.031821822198015!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xb1783b676afc1cfa!2z5LiK5rW35Lqk6YCa5aSn5a2m6L2v5Lu25a2m6Zmi!5e0!3m2!1sen!2skz!4v1621346193542!5m2!1sen!2skz" width="300" height="200" style="border:0;" allowfullscreen="" loading="lazy"></iframe></div>
        <div>
            <div id="join">
                <p id="join-soc">Join to Social Community</p>
                <span id="icons">
                    <ul>
                <li>
                    <!-- <a href="#"> <i class="fa fa-facebook" aria-hidden="true"></i> </a> -->
                    <a href="#"><font-awesome-icon :icon="['fab', 'weibo']"/></a>
                    <span></span><span></span><span></span><span></span>
                </li>
                <li>
                    <!-- <a href="#"> <i class="fa fa-twitter" aria-hidden="true"></i> </a> 
                    <span></span><span></span><span></span><span></span> -->
                    <a href="#"><font-awesome-icon :icon="['fab', 'alipay']"/></a>
                    <span></span><span></span><span></span><span></span>
                </li>
                <li>
                    <!-- <a href="#"> <i class="fa fa-google-plus" aria-hidden="true"></i> </a> 
                    <span></span><span></span><span></span><span></span> -->
                    <a href="#"><font-awesome-icon :icon="['fab', 'qq']"/></a>
                    <span></span><span></span><span></span><span></span>
                </li>
                <li>
                    <!-- <a href="#"> <i class="fa fa-linkedin" aria-hidden="true"></i> </a> 
                    <span></span><span></span><span></span><span></span> -->
                    <a href="#"><font-awesome-icon :icon="['fab', 'instagram']"/></a>
                    <span></span><span></span><span></span><span></span>
                </li>
                <li>
                    <!-- <a href="#"> <i class="fa fa-skype" aria-hidden="true"></i> </a> 
                    <span></span><span></span><span></span><span></span> -->
                    <a href="#"><font-awesome-icon :icon="['fab', 'tiktok']"/></a>
                    <span></span><span></span><span></span><span></span>
                </li>
             </ul>
                </span>
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