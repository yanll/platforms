import 'element-ui/lib/theme-default/index.css'
import ElementUI from 'element-ui'
import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App'
import routes from './scripts/routers'

Vue.use(ElementUI)
Vue.use(VueRouter)

import api from './scripts/api'
Vue.prototype.$api = api

const router = new VueRouter({
  routes
})

Vue.config.silent = true

/* eslint-disable no-new */
new Vue({
  el: '#app',
  template: '<App/>',
  components: {App},
  router
})
