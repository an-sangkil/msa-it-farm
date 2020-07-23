<template>
  <div class="container-fluid">
    <div class="fade-in">
      <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <!-- <div class="card-header"><i class="fa fa-align-justify"></i>
                     header
            </div> -->
            <div class="card-body">
            </div>
          </div>
          <div class="card">
            <div class="card-header">
              <div class="row align-items-center">
                <i class="fa fa-align-justify"></i>
                <div class="col-12 col-xl mb-3 mb-xl-0">Cattle List</div>
                <div class=" mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-success" type="button">등록</button>
                </div>
              </div>
            </div>
            <div class="card-body">
              <table class="table table-responsive-sm">
                <thead>
                <tr>
                  <th>entityNumber</th>
                  <th>indentityNumber</th>
                  <th>date</th>
                  <th>gender</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="item in cattleData" v-bind:key="item.localBeefManagementPK.entityManagementNumber+item.localBeefManagementPK.entityIdentificationNumber">
                  <td>{{ item.localBeefManagementPK.entityManagementNumber}}</td>
                  <td>{{ item.localBeefManagementPK.entityIdentificationNumber}}</td>
                  <td>{{ item.birthDay }}</td>
                  <td>{{ item.gender }}</td>
                  <td>Member</td>
                  <td><span class="badge badge-success">Active</span></td>
                </tr>
                </tbody>
              </table>
              <ul class="pagination">

                <div v-if="pagination.isPre">
                  <li class="page-item"><a class="page-link" href="#" v-on:click="pageMove(pagination.begin-2)">Prev</a></li>
                </div>

                <div v-for="(item,index) in pagingItem()">
                  <li class="page-item" :class="{active: item.active}"><a class="page-link" href="#" v-on:click="pageMove(item.value)">{{item.viewValue}}</a></li>
                </div>
                <div v-if="pagination.isNext">
                  <li class="page-item"><a class="page-link" href="#" v-on:click="pageMove(pagination.end)">Next</a></li>
                </div>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

  import PaginationUtils from '../../js/Pagination'

  export default {
    created() {

      this.$store.commit('changeHome', {name: 'Home'})
      this.$store.commit('changeTitle', {name: 'Cattle'})
      this.$store.commit('changeSubtitle', {name: 'list'})

    },

    data() {
      return {
        cattleData: []
        , pagination: {
          //mount 에서 데이터를 채워준다.
        }
        , pagingItem: function () {
          let list = [];
          for (let i = this.pagination.begin; i <= this.pagination.end; i++) {
            let item = {"value": i - 1, "viewValue": i, "active": this.CURRENT_NUMBER+1 === i}
            list.push(item)
          }
          return list;

        }
        , CURRENT_NUMBER: 0
        , BLOCK_PAGE_SIZE: 10
      }
    },
    mounted() {
      this.$http.get(this.$store.state.host + '/cattle/cattle_management_list')
        .then((res) => {
          //console.log("response = ",res)
          this.pagingProcess(res);
        })
        .catch((error) => {

        })
    },

    methods: {
      cattleSave: function () {
        this.$route.push('/cattle/cattleSave')
      },
      pageMove: function (currentPage) {

        //console.log("user click current page = ", currentPage)

        this.$http
          .get(this.$store.state.host + '/cattle/cattle_management_list?page=' + currentPage)
          .then((res) => {
            this.pagingProcess(res);
          })
          .catch((error) => {

          })
      }
      ,
      pagingProcess: function (res) {

        console.log(`response data = `,res.data)

        let responseData = res.data;
        let totalSize = responseData.totalPages

        // 페이지 네이션 구할때 현재 페이지에 + 1
        let currentNumber = responseData.pageable.pageNumber+1
        let calculatorPagination = PaginationUtils.Pagination(currentNumber, totalSize, PaginationUtils.DEFAULT_BLOCK_PAGE_SIZE)

        console.log('calculatorPagination', calculatorPagination)
        console.log('paging begin= ' + calculatorPagination.begin, `paging end = ${calculatorPagination.end}`);


        this.cattleData = responseData.contents
        this.pagination = calculatorPagination
        this.CURRENT_NUMBER = responseData.pageable.pageNumber

        console.log(`this.CURRENT_NUMBER = ${responseData.pageable.pageNumber}`)

      }
    }
  }

</script>

<style scoped="scoped">

</style>
