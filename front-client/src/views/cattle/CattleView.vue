<template>
  <div class="container-fluid">
    <div class="fade-in">
      <div class="row">

        <!-- cattle management (localbeef)  -->
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header"><strong>기본정보</strong>.</div>
            <div class="card-body">
              <form class="form-horizontal" action="" method="post" enctype="multipart/form-data">
                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">개체식별번호</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{entityIdentificationNumberView}}</p>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">관리번호</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{entityManagementNumberView}}</p>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">부 개체식별번호</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{parentPapaNo}}</p>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">모 개체식별번호</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{parentMomNo}}</p>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">태어난날</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{birthDay}}</p>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">입식일</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{enterDate}}</p>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">귀표장착일</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{earTagDate}}</p>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">거세일</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{castrationDate}}</p>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">성별</label>
                    <div class="col-md-9 col-form-label">
                      <p class="form-control-static">{{gender}}</p>
                    </div>
                  </div>

                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">판매여부</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{sellYn}}</p>
                    </div>
                  </div>
                </div>

                <div class="row">

                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">방번호</label>
                    <div class="col-md-9 col-form-label">
                      <p class="form-control-static">{{roomNumber}}</p>
                    </div>
                  </div>

                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">개월수</label>
                    <div class="col-md-9 col-form-label">
                      <p class="form-control-static">{{numberOfMonth}}</p>
                    </div>
                  </div>


                </div>

              </form>
            </div>
            <!--<div class="card-footer">
              <button class="btn btn-sm btn-primary" type="submit"> Submit</button>
              <button class="btn btn-sm btn-danger" type="reset"> Reset</button>
            </div>-->
          </div>
        </div>

        <!-- calves management -->
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header">
              <div class="row align-items-center">
                <i class="fa fa-align-justify"></i>
                <div class="col-12 col-xl mb-3 mb-xl-0">
                  <strong>분만정보</strong>
                </div>
                <div class=" mb-3 mb-xl-0">
                  <b-button id="show-btn" @click="$bvModal.show('calvesModal')" variant="outline-success">register</b-button>
                </div>
              </div>
            </div>
            <div class="card-body">
              <table class="table table-responsive-sm">
                <thead>
                <tr>
                  <th>번호</th>
                  <th>분만정보</th>
                  <th>년도</th>
                  <th>수정일</th>
                  <th>분만예정일</th>
                  <th>실제분만일</th>
                  <th>정액번호</th>
                  <th>특이사항</th>
                  <th>송아지개체식별번호</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="item in this.calvesManagementSet"
                    v-bind:key="item.calvesManagementPK.entityManagementNumber+item.calvesManagementPK.entityIdentificationNumber+item.calvesManagementPK.seq"
                    v-on:click="calvesDetail(item.calvesManagementPK.entityManagementNumber,item.calvesManagementPK.entityIdentificationNumber,item.calvesManagementPK.seq)"
                    style="cursor: pointer"
                >
                  <td>{{item.calvesManagementPK.seq}}</td>
                  <td>{{item.fertilizationIndex}}</td>
                  <td>{{item.years}}</td>
                  <td>{{item.fertilizationDate}}</td>
                  <td>{{item.expectedDateConfinement}}</td>
                  <td>{{item.calvingDate}}</td>
                  <td>{{item.spermNo}}</td>
                  <td>{{item.nothingSpecial}}</td>
                  <td>{{item.entityIdentificationNumberChild}}-{{item.entityManagementNumberChild}}</td>
                </tr>
                </tbody>
              </table>
              <!-- <div>
                 <b-table striped hover :items="calvesManagementSet" :fields="fields"></b-table>
               </div>-->

            </div>
            <!--<div class="card-footer"></div>-->
          </div>
        </div>

        <!-- Disease Treatment -->
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header">
              <div class="row align-items-center">
                <i class="fa fa-align-justify"></i>
                <div class="col-12 col-xl mb-3 mb-xl-0">
                  <strong>질병관리</strong>
                </div>
                <div class=" mb-3 mb-xl-0">
                  <b-button id="show-btn2" @click="$bvModal.show('diseaseModal')" variant="outline-success">register</b-button>
                </div>
              </div>
            </div>
            <div class="card-body">
              <table class="table table-responsive-sm">
                <thead>
                <tr>
                  <th>날짜</th>
                  <th>치료날짜</th>
                  <th>질병 명</th>
                  <th>약물 명</th>
                  <th>치료내역</th>
                  <th>투여방법</th>
                  <th>휴약기간</th>
                  <!--<th>needleLoseYn</th>-->

                </tr>
                </thead>
                <tbody>

                <tr v-for="item in this.diseaseTreatmentSet"
                    v-bind:key="item.diseaseTreatmentPK.entityManagementNumber+item.diseaseTreatmentPK.entityIdentificationNumber+item.diseaseTreatmentPK.day"
                    v-on:click="diseaseDetail(item.diseaseTreatmentPK.entityManagementNumber,item.diseaseTreatmentPK.entityIdentificationNumber,item.diseaseTreatmentPK.day)"
                    style="cursor: pointer"
                >
                  <td>{{item.diseaseTreatmentPK.day}}</td>
                  <td>{{item.cureDate}}</td>
                  <td>{{item.diseaseName}}</td>
                  <td>{{item.medicationName}}</td>
                  <td>{{item.treatmentDetails}}</td>
                  <td>{{item.injectionMethod}}</td>
                  <td>{{item.withdrawalPeriodExpirationDate}}</td>
                  <!--<td>{{item.needleLoseYn}}</td>-->
                </tr>
                </tbody>
              </table>

            </div>
            <!--<div class="card-footer"></div>-->
          </div>
        </div>

        <!-- buy information -->
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header"><strong>구입정보</strong>.</div>
            <div class="card-body">

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">구입처</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleBuyInformation.buyStoreName}}</p>
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">구입날짜</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleBuyInformation.buyDate}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">구입내역</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleBuyInformation.buyNote}}</p>
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">전화번호(사무실)</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleBuyInformation.buyPhoneNumber}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">전화번호(개인)</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleBuyInformation.buySellPhoneNumber}}</p>
                  </div>
                </div>
              </div>


            </div>
            <!--<div class="card-footer">
              <button class="btn btn-sm btn-primary" type="button"> Submit</button>
              &lt;!&ndash;<button class="btn btn-sm btn-danger" type="reset"> Reset</button>&ndash;&gt;
            </div>-->
          </div>
        </div>

        <!-- sell information -->
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header"><strong>판매정보</strong>.</div>
            <div class="card-body">

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">판매처</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.storeName}}</p>
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">판매날짜</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.sellDate}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">내역</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.sellNote}}</p>
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">전화번호</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.sellPhoneNumber}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">판매가격</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.sellingPrice}}</p>
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">liveweight</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.liveweight}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">dressedWeight</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.dressedWeight}}</p>
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">등급</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.beefGrade}}</p>
                  </div>
                </div>
              </div>


            </div>
            <div class="card-footer">
              <div class="row justify-content-end">
                <div class="col-6 col-sm-4 col-md-2 col-xl mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-info" type="button" v-on:click="cattleModifyForm()">modify</button>
                </div>
                <div class="col-6 col-sm-4 col-md-2 col-xl mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-info" type="button" v-on:click="cattleList()">list</button>
                </div>
              </div>

              <!--<button class="btn btn-sm btn-primary" type="button" v-on:click="cattleModifyForm()"> Submit</button>-->
              <!--<button class="btn btn-sm btn-danger" type="reset"> Reset</button>-->
            </div>
          </div>
        </div>


        <!-- calves /.modal-->
        <b-modal ref="calves-modal" id="calvesModal" size="xl" centered title="분만관리" @hidden="calvesResetModal" @ok="calvesSave">
          <b-container>
            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">년도</label>
                <div class="col-md-9">
                  <date-picker v-model="calvesManagement.years" value-type="format" format="YYYY" type="year" placeholder="Select year" style="width: 100%"></date-picker>
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">수정일</label>
                <div class="col-md-9">
                  <date-picker v-model="calvesManagement.fertilizationDate" format="YYYY-MM-DD HH:MM:SS" value-type="YYYY-MM-DD HH:MM:SS" lang="en" style="width: 100%"></date-picker>
                  <!--<b-form-input placeholder="Enter your fertilizationDate" type="text" v-model="calvesManagement.fertilizationDate"></b-form-input>-->
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">분만일</label>
                <div class="col-md-9">
                  <date-picker v-model="calvesManagement.calvingDate" format="YYYY-MM-DD HH:MM:SS" value-type="YYYY-MM-DD HH:MM:SS" lang="en" style="width: 100%"></date-picker>
                  <!--<b-form-input placeholder="Enter your calvingDate" type="date" v-model="calvesManagement.calvingDate"></b-form-input>-->
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">정액번호</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your spermNo" type="number" v-model="calvesManagement.spermNo"></b-form-input>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">특이사항</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your nothingSpecial" type="text" v-model="calvesManagement.nothingSpecial"></b-form-input>
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">송아지개체식별번호</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your child number" type="number" v-model="calvesManagement.child"></b-form-input>
                </div>
              </div>
            </div>
          </b-container>
        </b-modal>

        <b-modal ref="disease-modal" id="diseaseModal" size="xl" centered title="질병관리" @hidden="diseaseResetModal" @ok="diseaseSave">
          <b-container>
            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">질병관리 날짜</label>
                <div class="col-md-9">
                  <date-picker v-model="diseaseTreatment.cureDate" value-type="format" format="YYYY-MM-DD" type="date" placeholder="Select date" style="width: 100%"></date-picker>
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">질명 명</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your diseaseName" type="text" v-model="diseaseTreatment.diseaseName"></b-form-input>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">조치내역</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your name" v-model="diseaseTreatment.medicationName"></b-form-input>
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">상세 조치내역</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your treatmentDetails" v-model="diseaseTreatment.treatmentDetails"></b-form-input>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">투여방법</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your injectionMethod" type="text" v-model="diseaseTreatment.injectionMethod"></b-form-input>
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">휴약기간</label>
                <div class="col-md-9">
                  <date-picker v-model="diseaseTreatment.withdrawalPeriodExpirationDate" value-type="format" format="YYYY-MM-DD" type="date" placeholder="Enter your withdrawalPeriodExpirationDate" style="width: 100%"></date-picker>
                </div>
              </div>
            </div>
          </b-container>
        </b-modal>


      </div>
    </div>
  </div>
</template>
<script>

  import DatePicker from 'vue2-datepicker';
  import 'vue2-datepicker/index.css';

  export default {
    created() {

      this.$store.commit('changeHome', {name: 'Home'})
      this.$store.commit('changeTitle', {name: 'Cattle'})
      this.$store.commit('changeSubtitle', {name: 'View'})

    },
    components: {DatePicker},
    data() {
      return {
        entityIdentificationNumberView: ''
        , entityManagementNumberView: ''
        , entityIdentificationNumber: ''
        , entityManagementNumber: ''
        , parentPapaNo: ''
        , parentMomNo: ''
        , birthDay: ''
        , enterDate: ''
        , earTagDate: ''
        , castrationDate: ''
        , gender: 'MALE'
        , sellYn: 'N'
        , numberOfMonth: ''
        , roomNumber: ''
        , cattleBuyInformation: {
          buyStoreName: ''
          , buyDate: ''
          , buyNote: ''
          , buyPhoneNumber: ''
          , buySellPhoneNumber: ''
        }
        , cattleSellStoreInformation: {
          storeName: ''
          , sellDate: ''
          , sellNote: ''
          , sellPhoneNumber: ''
          , sellingPrice: ''
          , liveweight: ''
          , dressedWeight: ''
          , beefGrade: ''
        }

        , calvesManagementSet: []
        , diseaseTreatmentSet: []
        , calvesManagement: {calvesManagementPK: {}}
        , diseaseTreatment: {diseaseTreatmentPK: {}}

      }
    },
    mounted() {
      this.localBeefDetail()
    },
    computed: {},
    methods: {
      localBeefDetail: function () {

        let actionURL = `${this.$store.state.HOST}/cattle/detail?`
        console.log(`localBeefDetail : ${actionURL}`)

        this.$http.get(actionURL, {
          params: {
            entityManagementNumber: this.$route.query.entityNumber,
            entityIdentificationNumber: this.$route.query.identityNumber
          }
        }).then((res) => {

          console.log('rount response :', res)
          let responseData = res.data.detail.contents

          this.entityIdentificationNumber = responseData.localBeefManagementPK.entityIdentificationNumber
          this.entityIdentificationNumberView = `${this.entityIdentificationNumber.substring(0, 3)}-${this.entityIdentificationNumber.substring(3, 7)}-${this.entityIdentificationNumber.substring(7, 11)}-${this.entityIdentificationNumber.substring(11)}`

          this.entityManagementNumber = responseData.localBeefManagementPK.entityManagementNumber
          this.entityManagementNumberView = `${this.entityManagementNumber.substring(0, 4)}-${this.entityManagementNumber.substring(4)}`

          this.parentPapaNo = responseData.parentPapaNo
          this.parentMomNo = responseData.parentMomNo
          this.birthDay = responseData.birthDay
          this.enterDate = responseData.enterDate
          this.earTagDate = responseData.earTagDate
          this.castrationDate = responseData.castrationDate
          this.gender = responseData.gender
          this.sellYn = responseData.sellYn
          this.numberOfMonth = responseData.numberOfMonth
          this.roomNumber = responseData.roomNumber

          this.cattleBuyInformation = responseData.cattleBuyInformation
          this.cattleSellStoreInformation = responseData.cattleSellStoreInformation
          this.diseaseTreatmentSet = responseData.diseaseTreatmentSet
          this.calvesManagementSet = responseData.calvesManagementSet

        }).catch((error) => {
          console.log(error.message)
        })
      },
      cattleModifyForm: function () {
        let actionUrl = `/cattle/cattleForm?entityNumber=${this.entityManagementNumber}&identityNumber=${this.entityIdentificationNumber}`
        this.$router.push(actionUrl)
      }
      , calvesSave: function () {

        this.calvesManagement.calvesManagementPK.entityManagementNumber = this.entityManagementNumber
        this.calvesManagement.calvesManagementPK.entityIdentificationNumber = this.entityIdentificationNumber

        this.$http.put(this.$store.state.HOST + '/cattle/childbirth/save', this.calvesManagement)
          .then((res) => {
            console.log(res)
            if (res.data.status === "SUCCESS") {
              this.$http.get(this.$store.state.HOST + '/cattle/childbirth/list', {
                params: {
                  entityManagementNumber: this.entityManagementNumber,
                  entityIdentificationNumber: this.entityIdentificationNumber
                }
              }).then(({data}) => {
                this.calvesManagementSet = data.detail.contents
              })
            }
          }).catch((error) => {
          console.log(error)
        })

      }
      , calvesDetail: function (entityNumber, identityNumber, seq) {

        this.$http.get(this.$store.state.HOST + '/cattle/childbirth/detail', {
          params: {
            entityManagementNumber: entityNumber,
            entityIdentificationNumber: identityNumber,
            seq: seq
          }
        }).then(({data}) => {


          this.calvesManagement = data.detail.contents
          this.$refs['calves-modal'].show()


        })
      }, diseaseSave() {

        this.diseaseTreatment.diseaseTreatmentPK.entityManagementNumber = this.entityManagementNumber
        this.diseaseTreatment.diseaseTreatmentPK.entityIdentificationNumber = this.entityIdentificationNumber

        this.$http.put(this.$store.state.HOST + '/cattle/disease_treatment/save', this.diseaseTreatment)
          .then((res) => {

            if (res.data.status === "SUCCESS") {
              this.$http.get(this.$store.state.HOST + '/cattle/disease_treatment/list', {
                params: {
                  entityManagementNumber: this.entityManagementNumber,
                  entityIdentificationNumber: this.entityIdentificationNumber
                }
              }).then(({data}) => {
                console.log('data =', data)
                if (data.status === "SUCCESS") {
                  this.diseaseTreatmentSet = data.detail.contents
                }
              })
            }
          }).catch((error) => {
          console.log(error)
        })

      }, diseaseDetail: function (entityNumber, identityNumber, day) {

        this.$http.get(this.$store.state.HOST + '/cattle/disease_treatment/detail', {
          params: {
            entityManagementNumber: entityNumber,
            entityIdentificationNumber: identityNumber,
            day: day
          }
        }).then(({data}) => {

          if (data.status === 'SUCCESS') {

            debugger
            this.diseaseTreatment = data.detail.contents
            this.$refs['disease-modal'].show()
          }

        })
      }, cattleList() {

        this.$router.go(-1)

      }, calvesResetModal() {

        this.calvesManagement = {calvesManagementPK: {}}

      }, diseaseResetModal() {

        this.diseaseTreatment = {diseaseTreatmentPK: {}}

      }

    }
  }

</script>

<style scoped="scoped">

</style>
