import Vue from 'vue'
import Vuex from 'vuex'
import PaginationUtils from "../js/Pagination";
import axios from "../js/axios.intercepter";


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    home: 'HOME'
    , title: 'View'
    , subtitle: 'Dashboard'
    , host: process.env.VUE_APP_API_SERVER_HOST
    , pagingData: []
    , paginationValue: {}
    , paginationObject: []
    , CURRENT_NUMBER: 0
    , BLOCK_PAGE_SIZE: 10
    , VARIANT: 'success'
    , MESSAGE: 'save success'
    , DISMISS_SECS: 5
    , DISMISS_COUNT_DOWN: 0
  },
  mutations: {
    changeHome: (state, payload) => {
      return state.home = payload.name
    },
    changeTitle: (state, payload) => {
      return state.title = payload.name
    },
    changeSubtitle: (state, payload) => {
      return state.subtitle = payload.name
    },
    pagingProcess: function (state, payload) {
      try {
        let responseData = payload.data;
        let totalSize = responseData.totalPages
        let pageNumber = responseData.pageable.pageNumber;

        // 페이지 네이션 구할때 현재 페이지에 + 1
        let currentNumber = pageNumber + 1
        let calculatorPagination = PaginationUtils.Pagination(currentNumber, totalSize, PaginationUtils.DEFAULT_BLOCK_PAGE_SIZE)

        state.pagingData = responseData.contents
        state.paginationValue = calculatorPagination
        state.CURRENT_NUMBER = pageNumber

        let list = [];
        for (let i = calculatorPagination.begin; i <= calculatorPagination.end; i++) {
          let item = {"value": i - 1, "viewValue": i, "active": pageNumber + 1 === i}
          list.push(item)
        }
        state.paginationObject = list
      } catch (e) {
        console.log("", e)
      }
    },
    showAlert(state, {message, variant}) {
      console.log("show alert 실행")
      state.DISMISS_COUNT_DOWN = state.DISMISS_SECS
      state.VARIANT = variant
      state.MESSAGE = message
    }
  },
  actions: {
    pagingAction: (context, {actionUrl, parameters}) => {

      let state = context.state
      axios
        .get(state.host + actionUrl,
          {
            params: parameters
          }
        )
        .then((res) => {
          context.commit('pagingProcess', {"data": res.data.detail.data})
        })
        .catch((error) => {

        })

    }
  },
  modules: {}
})
