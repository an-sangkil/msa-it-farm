import { createStore } from "vuex"

export default createStore({
  state: {
    message: "Hello from Vuex Store!",
    counter: 0,
    user: null
  },
  mutations: {
    INCREMENT(state) {
      state.counter++
    },
    DECREMENT(state) {
      state.counter--
    },
    SET_MESSAGE(state, payload) {
      state.message = payload
    },
    SET_USER(state, user) {
      state.user = user
    }
  },
  actions: {
    increment({ commit }) {
      commit("INCREMENT")
    },
    decrement({ commit }) {
      commit("DECREMENT")
    },
    updateMessage({ commit }, message) {
      commit("SET_MESSAGE", message)
    }
  },
  getters: {
    getMessage: (state) => state.message,
    getCounter: (state) => state.counter,
    isLoggedIn: (state) => !!state.user
  }
})
