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
                        <div v-for="value in 12">
                          <a class="dropdown-item" href="#" @click="diaryPeriodCalculation(value)">{{value}}월</a>
                        </div>

                      </div>
                      <button class="btn btn-outline-info" :class="{active:searchDateType.active_period_date}" type="button" @click="scheduleSearchTypeSelection('active_period_date')">기간선택</button>

                    </div>
                    <div class="col-12 col-xl mb-3 mb-xl-0">
                      <b-form inline v-if="periodForm">
                        <date-picker v-model="periodDate" value-type="format" format="YYYY-MM-DD" range placeholder="Select date range"></date-picker>
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
    </div>
  </div>

</template>

<script>

</script>
