import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "./js/axios.intercepter"
import VueSession from 'vue-session'
import {BootstrapVue,ToastPlugin,IconsPlugin} from 'bootstrap-vue'
import { BToast } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import './css/style.css'
Vue.component('b-toast', BToast)
Vue.use(ToastPlugin)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)


// 세션 옵션
const sessionOptions = {
  persist: true
}
Vue.use(VueSession, sessionOptions)


// vue-coreui template
Vue.config.productionTip = false
Vue.config.performance = true
Vue.prototype.$log = console.log.bind(console)

// 기타 플러그인
Vue.prototype.$http = axios


new Vue({
  router,
  VueSession,
  sessionOptions,
  store,
  render: h => h(App)
}).$mount('#app')
