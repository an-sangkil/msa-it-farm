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
                  <button class="btn btn-block btn-outline-success" type="button" v-on:click="cattleSave">register</button>
                </div>
              </div>
            </div>
            <div class="card-body">
              <table class="table table-responsive-sm">
                <thead>
                <tr>
                  <th>entityNumber/identityNumber</th>
                  <th>roomNumber</th>
                  <th>calvesCount</th>
                  <th>calvesDate</th>
                  <th>gender</th>
                  <th>birthDate</th>
                  <th>number of month</th>
                  <th>mother iden-n</th>
                  <th>care history</th>
                  <th>care date</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="item in this.$store.state.pagingData"
                    v-bind:key="item.localBeefManagementPK.entityManagementNumber+item.localBeefManagementPK.entityIdentificationNumber">
                  <td v-on:click="detailView(item.localBeefManagementPK.entityManagementNumber,item.localBeefManagementPK.entityIdentificationNumber)" style="cursor: pointer">
                    <a href="javascript:void (0);">
                      {{ item.localBeefManagementPK.entityManagementNumber}}
                      -
                      {{ item.localBeefManagementPK.entityIdentificationNumber}}
                    </a>
                  </td>
                  <td>{{ item.roomNumber == null ? '-':item.roomNumber}}</td>
                  <td>{{ item.calvesCount}}</td>
                  <td>{{ item.expectedDateConfinement }}</td>
                  <td>{{ item.gender }}</td>
                  <td>{{ item.birthDay }}</td>
                  <td>{{ item.numberOfMonth}}</td>
                  <td>{{ item.parentMomNo}}</td>
                  <td></td>
                  <td></td>
                </tr>
                </tbody>
              </table>
              <ul class="pagination justify-content-center">

                <div v-if="isPre">
                  <li class="page-item"><a class="page-link" href="#" v-on:click="pagingMove(begin-2)">Prev</a>
                  </li>
                </div>

                <div v-for="(item,index) in pagination">
                  <li class="page-item" :class="{active: item.active}"><a class="page-link" href="#"
                                                                          v-on:click="pagingMove(item.value)">{{item.viewValue}}</a>
                  </li>
                </div>
                <div v-if="isNext">
                  <li class="page-item"><a class="page-link" href="#" v-on:click="pagingMove(end)">Next</a>
                  </li>
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
      return {}
    },
    computed: {
      contents() {
        return this.$store.state.pagingData;
      },
      isPre: function () {
        return this.$store.state.paginationValue.isPre;
      },
      isNext() {
        return this.$store.state.paginationValue.isNext;
      },
      begin() {
        return this.$store.state.paginationValue.begin;
      },
      end() {
        return this.$store.state.paginationValue.end;
      },
      pagination() {
        return this.$store.state.paginationObject;
      }
    },
    mounted() {
      this.pagingMove(0);
    },

    methods: {
      cattleSave: function () {
        this.$router.push('/cattle/cattleForm')
      },
      pagingMove: function (currentPage) {

        let actionUrl = '/cattle/cattle_management_list?page=' + currentPage+'&size=20';
        this.$store.dispatch('pagingAction', {actionUrl: actionUrl})
      },
      detailView: function (entityNumber, identityNumber) {
        this.$router.push(`/cattle/cattleDetail?entityNumber=${entityNumber}&identityNumber=${identityNumber}`)

      }
    }
  }

</script>

<style scoped="scoped">

</style>
