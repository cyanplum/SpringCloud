// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios';
import 'element-ui/lib/theme-chalk/index.css'
import Element from 'element-ui'
import './axios'
import store from './store'

Vue.config.productionTip = false
Vue.use(Element)
Vue.prototype.$axios = axios
Vue.prototype.$store = store

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
