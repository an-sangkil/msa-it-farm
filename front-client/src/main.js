import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios"

// vue-coreui template
Vue.config.productionTip = false
Vue.prototype.$http = axios

// vue-coreui template
Vue.config.performance = true
Vue.prototype.$log = console.log.bind(console)


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
