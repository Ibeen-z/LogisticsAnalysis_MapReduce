import Vue from 'vue'
import App from './App.vue'
import dataV from '@jiaminghi/data-view'
import echarts from 'echarts'
import axios from 'axios';
Vue.prototype.$echarts = echarts
Vue.prototype.$http = axios.create({
  timeout: 3000
})
import {VueJsonp} from 'vue-jsonp'
Vue.use(VueJsonp)

Vue.use(dataV)
Vue.config.productionTip = false
new Vue({
  render: h => h(App),
}).$mount('#app')
