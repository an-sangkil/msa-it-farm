import Vue from 'vue'
import Vuex from 'vuex'
import PaginationUtils from "../js/Pagination";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    home: 'HOME'
    , title: 'Admin'
    , subtitle: 'Dashboard'
    , host: process.env.VUE_APP_API_SERVER_HOST
    , pagingData: []
    , paginationValue: {}
    , paginationObject: []
    , CURRENT_NUMBER: 0
    , BLOCK_PAGE_SIZE: 10

  },
  mutations: {
    changeHome: (state, payload) => {
      //console.log(payload)
      return state.home = payload.name
    },
    changeTitle: (state, payload) => {
      //console.log(payload)
      return state.title = payload.name
    },
    changeSubtitle: (state, payload) => {
      //console.log(payload)
      return state.subtitle = payload.name
    },
    pagingProcess: function (state, payload) {

      //console.log(`response data = `, payload.data)

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
      }catch (e) {
        console.log("",e)
      }
    }
  },
  actions: {},
  modules: {}
})
