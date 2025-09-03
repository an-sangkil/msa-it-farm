console.log('🟡 MAIN.JS START:', new Date().toISOString())

import { createApp } from 'vue'
console.log('🟢 Vue imported successfully')

import App from './App.vue'
console.log('🟢 App component imported')

import router from './router'
console.log('🟢 Router imported')

import store from './store'
console.log('🟢 Store imported')

import 'bootstrap/dist/css/bootstrap.min.css'
console.log('🟢 Bootstrap CSS loaded')

import i18n from './i18n'
console.log('🟢 i18n imported')

console.log('🟡 Creating Vue app...')
const app = createApp(App)
console.log('🟢 Vue app created:', app)

console.log('🟡 Adding router, store and i18n...')
app.use(router)
app.use(store)
app.use(i18n)

app.config.globalProperties.$log = console.log.bind(console)

console.log('🟡 Mounting app to #app...')
try {
  const appElement = document.getElementById('app')
  console.log('🟢 App element found:', appElement)
  
  app.mount('#app')
  console.log('🟢 APP MOUNTED SUCCESSFULLY!')
} catch (error) {
  console.error('🔴 MOUNT ERROR:', error)
}
