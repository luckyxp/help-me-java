import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import instance from './http'
 
Vue.prototype.$axios = instance

Vue.config.productionTip = false
Vue.use(ElementUI);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


  // 清除token,返回登录页面
// localStorage.removeItem('Authorization');
// this.$router.push('/login');