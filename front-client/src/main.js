import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios"

// vue-coreui template
import 'core-js/stable'
import CoreuiVue from '@coreui/vue'
import { iconsSet as icons } from './assets/icons/icons.js'

Vue.config.productionTip = false
Vue.prototype.$http = axios

// vue-coreui template
Vue.config.performance = true
Vue.use(CoreuiVue)
Vue.prototype.$log = console.log.bind(console)


new Vue({
  router,
  store,
  icons,
  render: h => h(App)
}).$mount('#app')
