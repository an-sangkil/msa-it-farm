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
              <b-row class="form-group" cols="12" cols-sm="12">
                <b-col cols="12" sm="4">
                  <b-row>
                    <b-col sm="2">
                      <label for="input-identityId">개체식별번호</label>
                    </b-col>
                    <b-col sm="10">
                      <b-form-input id="input-identityId" type="number" placeholder="Enter your identityId" v-model="entityIdentificationNumber"></b-form-input>
                    </b-col>
                  </b-row>
                </b-col>
                <b-col cols="12" sm="4">
                  <b-row>
                    <b-col sm="2">
                      <label for="input-gender">암수구분</label>
                    </b-col>
                    <b-col sm="10">
                      <select class="form-control" id="input-gender" v-model="gender">
                        <option value="">전체</option>
                        <option value="MALE">수컷</option>
                        <option value="FEMALE">암컷</option>
                      </select>
                    </b-col>
                  </b-row>
                </b-col>
                <b-col sm="4">
                  <b-row>
                    <b-col sm="2">
                      <label for="input-roomNumber">사육방</label>
                    </b-col>
                    <b-col sm="10">
                      <b-form-input id="input-roomNumber" placeholder="Enter your roomNumber" v-model="roomNumber"></b-form-input>
                    </b-col>
                  </b-row>
                </b-col>
              </b-row>

              <b-row class="form-group" cols="12">
                <b-col sm="4">
                  <b-row>
                    <b-col sm="2">
                      <label for="input-numberOfMonth">개월수</label>
                    </b-col>
                    <b-col sm="10">
                      <b-form-input id="input-numberOfMonth" type="number" placeholder="Enter your numberOfMonth" v-model="numberOfMonth"></b-form-input>
                    </b-col>
                  </b-row>
                </b-col>
                <b-col sm="4">
                  <b-row>
                    <b-col sm="2">
                      <label for="input-birthDate">생년월일</label>
                    </b-col>
                    <b-col sm="10">
                      <date-picker v-model="birthDate" value-type="format" format="YYYY-MM-DD" placeholder="Select date range" range style="width: 100%"></date-picker>
                    </b-col>
                  </b-row>
                </b-col>
                <b-col sm="4">
                  <b-row>
                    <b-col sm="2">
                      <label for="input-birthDate">-</label>
                    </b-col>
                    <b-col sm="10">

                    </b-col>
                  </b-row>
                </b-col>
              </b-row>
            </div>
            <div class="card-footer">
              <div class="row justify-content-end">
                <div class="col-6 col-sm-4 col-md-2 col-xl mb-3 mb-xl-0"></div>
                <div class="col-6 col-sm-4 col-md-2 col-xl mb-3 mb-xl-0"></div>
                <div class="col-6 col-sm-4 col-md-2 col-xl mb-3 mb-xl-0">
                  <b-button block variant="outline-info" type="button" @click="searchCattle()">검색</b-button>
                </div>
              </div>
            </div>
          </div>

          <div class="card">
            <div class="card-header">
              <div class="row align-items-center">
                <i class="fa fa-align-justify"></i>
                <div class="col-12 col-xl mb-3 mb-xl-0">Cattle List</div>
                <div class=" mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-success" type="button" v-on:click="cattleSave">등록</button>
                </div>
              </div>
            </div>
            <div class="card-body">
              <table class="table table-responsive-sm">
                <thead>
                <tr>
                  <th>개체 식별번호</th>
                  <th>관리번호</th>
                  <th>사육방</th>
                  <th>분만횟수</th>
                  <th>수정횟수</th>
                  <th>분만예정일</th>
                  <th>암수구분</th>
                  <th>생년월일</th>
                  <th>개월수</th>
                  <th>모 개체식별 번호</th>
                  <th>치료내역</th>
                  <th>치료날짜</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="item in this.$store.state.pagingData"
                    v-bind:key="item.localBeefManagementPK.entityManagementNumber+item.localBeefManagementPK.entityIdentificationNumber">
                  <td v-on:click="detailView(item.localBeefManagementPK.entityManagementNumber,item.localBeefManagementPK.entityIdentificationNumber)" style="cursor: pointer">
                    <a href="javascript:void (0);">
                      {{
                      `${item.localBeefManagementPK.entityIdentificationNumber.substring(0,3)}-${item.localBeefManagementPK.entityIdentificationNumber.substring(3,7)}-${item.localBeefManagementPK.entityIdentificationNumber.substring(7,11)}-${item.localBeefManagementPK.entityIdentificationNumber.substring(11)}`}}
                    </a>
                  </td>
                  <td v-on:click="detailView(item.localBeefManagementPK.entityManagementNumber,item.localBeefManagementPK.entityIdentificationNumber)" style="cursor: pointer">
                    <a href="javascript:void (0);">
                      {{ `${item.localBeefManagementPK.entityManagementNumber.substring(0,4)}-${item.localBeefManagementPK.entityManagementNumber.substring(4,8)}`}}
                    </a>
                  </td>
                  <td>{{ item.roomNumber == null ? '-':item.roomNumber}}</td>
                  <td>{{ item.calvesCount-1}}</td>
                  <td>{{ item.inseminationCount}}</td>
                  <td>{{ item.expectedDateChildbirth }}</td>
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
  import DatePicker from 'vue2-datepicker';
  import 'vue2-datepicker/index.css';
  import moment from 'moment';


  export default {
    components: {
      DatePicker
    },
    created() {

      this.$store.commit('changeHome', {name: 'Home'})
      this.$store.commit('changeTitle', {name: 'Cattle'})
      this.$store.commit('changeSubtitle', {name: 'list'})

    },

    data() {
      return {
        entityIdentificationNumber: '',
        gender: '',
        numberOfMonth: '',
        roomNumber: '',
        birthDate: ''

      }
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

        let betweenBirthDate;
        if (this.birthDate.length > 0 && this.birthDate[0] != null)
          betweenBirthDate = this.birthDate[0] + "@@" + this.birthDate[1]

        let actionUrl = '/cattle/cattle_management_list?page=' + currentPage + '&size=20';
        this.$store.dispatch('pagingAction', {
          actionUrl: actionUrl,
          parameters: {
            entityIdentificationNumber: this.entityIdentificationNumber,
            gender: this.gender,
            numberOfMonth: this.numberOfMonth,
            roomNumber: this.roomNumber,
            birthDate: betweenBirthDate
          },
        })
      },
      detailView: function (entityNumber, identityNumber) {
        this.$router.push(`/cattle/cattleDetail?entityNumber=${entityNumber}&identityNumber=${identityNumber}`)

      }
      , searchCattle() {

        this.pagingMove(0)

      }
    }
  }

</script>

<style scoped="scoped">

</style>
