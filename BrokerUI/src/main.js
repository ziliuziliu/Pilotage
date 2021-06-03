import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';


Vue.use(Antd);
Vue.use(ElementUI);

import router from "./router.js"


Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});

// new Vue({
//   render: h => h(App),
// }).$mount('#app')
