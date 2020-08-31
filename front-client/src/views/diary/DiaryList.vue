<template>
  <div class="container-fluid">
    <div class="fade-in">
      <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <!--<div class="card-header">
              <div class="row align-items-center">
              </div>
            </div>-->

            <div class="card-body">
              <div class="row align-items-center">
                <b-container fluid="">
                  <div class="col-md-12 row">
                    <!--<div class="mb-auto mb-xl-0">search period</div>-->

                    <div class="btn-group col-12 col-xl mb-3 mb-xl-0 " role="group" aria-label="Basic example">
                      <button class="btn btn-outline-info" :class="{active:searchDateType.active_one_month}" type="button" @click="scheduleSearchTypeSelection('active_one_month')">1개월</button>
                      <button class="btn btn-outline-info" :class="{active:searchDateType.active_two_month}" type="button" @click="scheduleSearchTypeSelection('active_two_month')">3개월</button>

                      <button class="btn btn-outline-info dropdown-toggle" :class="{active:searchDateType.active_three_month}" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">월별</button>
                      <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="margin: 0px;">
                        <div v-for="value in 12" >
                          <a class="dropdown-item" href="#" @click="diaryPeriodCalculation(value)">{{value}}월</a>
                        </div>

                      </div>
                      <button class="btn btn-outline-info" :class="{active:searchDateType.active_period_date}" type="button" @click="scheduleSearchTypeSelection('active_period_date')">기간선택</button>

                    </div>
                    <div class="col-12 col-xl mb-3 mb-xl-0">
                      <b-form inline v-if="periodForm">
                        <date-picker v-model="periodDate" value-type="format" format="YYYY-MM-DD" range placeholder="기간을 선택해주세요."></date-picker>
                        <b-button variant="outline-info" type="button" v-on:click="periodSearch()">search</b-button>
                      </b-form>
                    </div>
                  </div>
                </b-container>
              </div>

            </div>
            <!--<div class="card-footer"></div>-->
          </div>
        </div>
      </div>

      <b-row>
        <b-col cols="12">
          <div class="card">
            <div class="card-header">
              <div class="row align-items-center">
                <i class="fa fa-align-justify"></i>
                <div class="col-12 col-xl mb-3 mb-xl-0"></div>
                <div class=" mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-success" type="button" v-on:click="scheduleForm()">register</button>
                </div>
              </div>
            </div>

            <!--<div class="card-body">
            </div>
            <div class="card-footer">
            </div>-->
          </div>

        </b-col>

      </b-row>

      <div class="row" v-for="item in scheduleData">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header"><i class="fa fa-align-justify"></i>
              <input class="form-control" id="uuid" type="hidden" name="text-input" v-model="item.uuid">
              <input class="form-control" id="seq" type="hidden" name="text-input" v-model="item.seq">
              <h2 style="cursor: pointer" v-on:click="scheduleDetail(item.uuid,item.seq)">
                <a href="javascript:return false;">{{item.standardDate}} ({{item.seq}})</a>
              </h2>
            </div>
            <div class="card-body">

              <div class="row">
                <div class="form-group col-md-4 row">
                  <label class="col-md-3 col-form-label">날짜</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.standardDate}}</p>
                  </div>
                </div>
                <!--<div class="form-group col-md-4 row" style="visibility: hidden">
                  <label class="col-md-3 col-form-label"  >seq</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.seq}}</p>
                  </div>
                </div>-->
                <div class="form-group col-md-4 row">
                  <label class="col-md-3 col-form-label">생성시간</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.createdTime}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-4 row">
                  <label class="col-md-3 col-form-label">날씨</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.todayWeatherCode}}</p>
                  </div>
                </div>
                <div class="form-group col-md-4 row">
                  <label class="col-md-3 col-form-label">최저온도</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.minTemperatureType}} {{item.minimumTemperature}}</p>
                  </div>
                </div>
                <div class="form-group col-md-4 row">
                  <label class="col-md-3 col-form-label">최고온도</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.maxTemperatureType}} {{item.maximumTemperature}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-12 row">
                  <label class="col-md-1 col-form-label">제목</label>
                  <div class="col-md-11">
                    <p class="form-control-static">{{item.subject}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-12 row">
                  <label class="col-md-1 col-form-label">내용</label>
                  <div class="col-md-11">
                    <p class="form-control-static">{{item.content}}</p>
                  </div>
                </div>
              </div>


            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import DatePicker from 'vue2-datepicker';
  import 'vue2-datepicker/index.css';
  import moment from 'moment';

  export default {
    components:{
      DatePicker
    },
    created() {

      this.$store.commit('changeHome', {name: 'Home'})
      this.$store.commit('changeTitle', {name: 'Schedule'})
      this.$store.commit('changeSubtitle', {name: 'List'})

    },

    data() {
      return {
        startDate: null,
        endDate: null,
        scheduleData: [],
        searchDateType: {
          active_one_month: true,
          active_two_month: false,
          active_three_month: false,
          active_period_date: false,
        },
        periodForm: false,
        periodDate: [],
      }
    },
    mounted() {
      let now = moment()
      this.endDate = now.add(1, 'days').format('YYYY-MM-DD');
      this.startDate = now.subtract(1, 'months').format('YYYY-MM-DD')

      this.diarySearch()

    },
    computed: {},
    methods: {
      scheduleForm: function () {

        this.$router.push("/diary/diaryForm");

      }, searchButtonReset() {
        // 초기화
        for (const key in this.searchDateType) {
          this.searchDateType[key] = false;
        }
        this.periodForm = false;
      }
      , scheduleSearchTypeSelection: function (searchType, month) {

        this.searchButtonReset()

        let now = moment()
        this.endDate = now.add(1, 'days').format('YYYY-MM-DD');

        switch (searchType) {
          case 'active_one_month' :
            this.searchDateType.active_one_month = true
            this.startDate = now.subtract(1, 'months').format('YYYY-MM-DD')
            break;
          case 'active_two_month' :
            this.searchDateType.active_two_month = true
            this.startDate = now.subtract(3, 'months').format('YYYY-MM-DD')
            break;
          case 'active_three_month' :
            this.searchDateType.active_three_month = true
            this.startDate = now.subtract(3, 'months').format('YYYY-MM-DD')
            break;
          case 'active_period_date' :
            this.searchDateType.active_period_date = true
            this.periodForm = true
            break;
          default:
            this.searchDateType.active_one_month = true
        }

        this.diarySearch()

      },
      diarySearch() {

        console.log(this.startDate, this.endDate)

        this.$http.get(this.$store.state.HOST + '/diary/list', {
          params: {
            start_date: this.startDate,
            end_date: this.endDate
          }
        })
          .then((res) => {
            console.log(res.data)
            this.scheduleData = res.data.detail.contents

          }).catch((error) => {
          console.log(error)
        })
      }
      , scheduleDetail: function (uuid, seq) {
        let actionURL = `/diary/diaryDetail?uuid=${uuid}&seq=${seq}`
        this.$router.push(actionURL)

      }
      , diaryPeriodCalculation: function (month) {
        this.searchButtonReset()

        const startOfMonth = moment().month(month - 1).startOf('month').format('YYYY-MM-DD')
        const endOfMonth = moment().month(month - 1).endOf('month').format('YYYY-MM-DD')

        this.startDate = startOfMonth
        this.endDate = endOfMonth

        this.diarySearch()

      }, periodSearch(){

        this.startDate = this.periodDate[0]
        this.endDate = this.periodDate[1]

        this.diarySearch()
      }
    }
  }
</script>

<style>

</style>
