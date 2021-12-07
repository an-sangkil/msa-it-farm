import Vue from 'vue'
import Vuex from 'vuex'
import PaginationUtils from '../js/Pagination'
import axios from '../js/axios.intercepter'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    HOME: 'HOME',
    TITLE: 'View',
    SUBTITLE: 'Dashboard',
    HOST: process.env.VUE_APP_API_SERVER_HOST,
    pagingData: [],
    paginationValue: {},
    paginationObject: [],
    CURRENT_NUMBER: 0,
    BLOCK_PAGE_SIZE: 10,
    VARIANT: 'success',
    MESSAGE: 'save success',
    DISMISS_SECS: 5,
    DISMISS_COUNT_DOWN: 0,
    IS_AUTH: false
  },
  mutations: {
    changeHome: (state, payload) => {
      return state.HOME = payload.name
    },
    changeTitle: (state, payload) => {
      return state.TITLE = payload.name
    },
    changeSubtitle: (state, payload) => {
      return state.SUBTITLE = payload.name
    },
    pagingProcess: function (state, payload) {
      try {
        const responseData = payload.data
        const totalSize = responseData.totalPages
        const pageNumber = responseData.pageable.pageNumber

        // 페이지 네이션 구할때 현재 페이지에 + 1
        const currentNumber = pageNumber + 1
        const calculatorPagination = PaginationUtils.Pagination(currentNumber, totalSize, PaginationUtils.DEFAULT_BLOCK_PAGE_SIZE)

        state.pagingData = responseData.contents
        state.paginationValue = calculatorPagination
        state.CURRENT_NUMBER = pageNumber

        const list = []
        for (let i = calculatorPagination.begin; i <= calculatorPagination.end; i++) {
          const item = {
            value: i - 1,
            viewValue: i,
            active: pageNumber + 1 === i
          }
          list.push(item)
        }
        state.paginationObject = list
      } catch (e) {
        console.log('', e)
      }
    },
    showAlert(state, {message, variant}) {

      state.DISMISS_COUNT_DOWN = state.DISMISS_SECS
      state.VARIANT = variant
      state.MESSAGE = message
    }
    ,
    createAuthentication: (state, {auth}) => {
      state.IS_AUTH = auth
    }
  },

  actions: {
    pagingAction: (context, {actionUrl, parameters}) => {
      const state = context.state
      axios
        .get(state.HOST + actionUrl,
          {
            params: parameters
          }
        )
        .then((res) => {
          context.commit('pagingProcess', {data: res.data.detail.contents})
        })
        .catch((error) => {
          console.log('pagingAction error', e.messages)
        })
    }
  },
  modules: {}
  ,
  getters: {
    isAuth: (state) => {
      return state.IS_AUTH
    }
  }

})
