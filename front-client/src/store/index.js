import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    home:'HOME',
    title:'Admin',
    subtitle:'Dashboard',
    host: process.env.VUE_APP_API_SERVER_HOST

  },
  mutations: {
      changeHome: (state,payload) => {
        console.log(payload)
        return state.home=payload.name
      },
      changeTitle: (state,payload) => {
        console.log(payload)
        return state.title=payload.name
      },
      changeSubtitle: (state,payload) => {
        console.log(payload)
        return state.subtitle=payload.name
      }
  },
  actions: {
  },
  modules: {
  }
})
