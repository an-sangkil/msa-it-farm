<template>
  <div class="container-fluid">
    <div class="fade-in">
      <div class="row">

        <!-- cattle management (localbeef)  -->
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header"><strong>Basic Form</strong> Elements</div>
            <div class="card-body">
              <form class="form-horizontal" action="" method="post" enctype="multipart/form-data">
                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">entityNumber</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{entityManagementNumber}}</p>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">identityNumber</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{entityIdentificationNumber}}</p>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">parentPapaNo</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{parentPapaNo}}</p>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">parentMomNo</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{parentMomNo}}</p>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">birthDay</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{birthDay}}</p>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">enterDate</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{enterDate}}</p>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">earTagDate</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{earTagDate}}</p>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">castrationDate</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{castrationDate}}</p>
                    </div>
                  </div>
                </div>


                <div class="row">

                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">genders</label>
                    <div class="col-md-9 col-form-label">
                      <p class="form-control-static">{{gender}}</p>
                    </div>
                  </div>

                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">sellYn</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{sellYn}}</p>
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
                  <strong>calves List</strong>
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
                  <th>seq</th>
                  <th>fertilizationIndex</th>
                  <th>years</th>
                  <th>fertilizationDate</th>
                  <th>expectedDateConfinement</th>
                  <th>calvingDate</th>
                  <th>spermNo</th>
                  <th>calvingDate</th>
                  <th>nothingSpecial</th>
                  <th>child</th>
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
                  <td>{{item.calvingDate}}</td>
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
                  <strong>Disease Form</strong> Elements
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
                  <th>seq</th>
                  <th>cureDate</th>
                  <th>diseaseName</th>
                  <th>medicationName</th>
                  <th>treatmentDetails</th>
                  <th>injectionMethod</th>
                  <th>withdrawalPeriodExpirationDate</th>
                  <th>needleLoseYn</th>

                </tr>
                </thead>
                <tbody>

                <tr v-if:this.calvesManagementSet!=null v-for="item in this.diseaseTreatmentSet"
                    v-bind:key="item.diseaseTreatmentPK.entityManagementNumber+item.diseaseTreatmentPK.entityIdentificationNumber+item.diseaseTreatmentPK.seq">
                  <td v-on:click="calvesDetail(item.diseaseTreatmentPK.entityManagementNumber,item.diseaseTreatmentPK.entityIdentificationNumber,item.diseaseTreatmentPK.seq)">{{item.diseaseTreatmentPK.seq}}</td>
                  <td>{{item.cureDate}}</td>
                  <td>{{item.diseaseName}}</td>
                  <td>{{item.medicationName}}</td>
                  <td>{{item.treatmentDetails}}</td>
                  <td>{{item.injectionMethod}}</td>
                  <td>{{item.withdrawalPeriodExpirationDate}}</td>
                  <td>{{item.needleLoseYn}}</td>
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
            <div class="card-header"><strong>BuyInformation Form</strong> Elements</div>
            <div class="card-body">

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">buyStoreName</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleBuyInformation.buyStoreName}}</p>
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">buyDate</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleBuyInformation.buyDate}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">buyNote</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleBuyInformation.buyNote}}</p>
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">buyPhoneNumber</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleBuyInformation.buyPhoneNumber}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">buySellPhoneNumber</label>
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
            <div class="card-header"><strong>sell Form</strong> Elements</div>
            <div class="card-body">

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">storeName</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.storeName}}</p>
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">sellDate</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.sellDate}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">sellNote</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.sellNote}}</p>
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">sellPhoneNumber</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.sellPhoneNumber}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">sellingPrice</label>
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
                  <label class="col-md-3 col-form-label">beefGrade</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{cattleSellStoreInformation.beefGrade}}</p>
                  </div>
                </div>
              </div>


            </div>
            <div class="card-footer">
              <div class="row justify-content-end">
                <div class="mb-3 mb-xl-0">
                  <button class="btn btn-block btn-info" type="button" v-on:click="cattleModifyForm()">modify</button>
                </div>
              </div>

              <!--<button class="btn btn-sm btn-primary" type="button" v-on:click="cattleModifyForm()"> Submit</button>-->
              <!--<button class="btn btn-sm btn-danger" type="reset"> Reset</button>-->
            </div>
          </div>
        </div>


        <!-- calves /.modal-->
        <b-modal ref="calves-modal" id="calvesModal" size="xl" centered title="calves form" @hidden="calvesResetModal">
          <b-container>
            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">years</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your years" type="number" v-model="calvesManagement.years"></b-form-input>
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">fertilizationDate</label>
                <div class="col-md-9">
                  <date-picker v-model="calvesManagement.fertilizationDate" format="YYYY-MM-DD HH:MM:SS"  value-type="YYYY-MM-DD HH:MM:SS"  lang="en" ></date-picker>
                  <!--<b-form-input placeholder="Enter your fertilizationDate" type="text" v-model="calvesManagement.fertilizationDate"></b-form-input>-->
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">calvingDate</label>
                <div class="col-md-9">
                  <date-picker v-model="calvesManagement.calvingDate" format="YYYY-MM-DD HH:MM:SS"  value-type="YYYY-MM-DD HH:MM:SS"  lang="en" ></date-picker>
                  <!--<b-form-input placeholder="Enter your calvingDate" type="date" v-model="calvesManagement.calvingDate"></b-form-input>-->
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">spermNo</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your spermNo" type="number" v-model="calvesManagement.spermNo"></b-form-input>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">nothingSpecial</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your nothingSpecial" type="text" v-model="calvesManagement.nothingSpecial"></b-form-input>
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">child</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your child number" type="number" v-model="calvesManagement.child"></b-form-input>
                </div>
              </div>
            </div>
          </b-container>
        </b-modal>

        <b-modal ref="disease-modal" id="diseaseModal" size="xl" centered title="disease form" @hidden="diseaseResetModal">
          <b-container>
            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">cureDate</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your cureDate" type="date" v-model="diseaseTreatment.cureDate"></b-form-input>
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">diseaseName</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your diseaseName" type="text" v-model="diseaseTreatment.diseaseName"></b-form-input>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">medicationName</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your name" v-model="diseaseTreatment.medicationName"></b-form-input>
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">treatmentDetails</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your treatmentDetails" v-model="diseaseTreatment.treatmentDetails"></b-form-input>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">injectionMethod</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your injectionMethod" type="text" v-model="diseaseTreatment.injectionMethod"></b-form-input>
                </div>
              </div>
              <div class="form-group col-md-6 row">
                <label class="col-md-3 col-form-label">withdrawalPeriodExpirationDate</label>
                <div class="col-md-9">
                  <b-form-input placeholder="Enter your withdrawalPeriodExpirationDate" type="text" v-model="diseaseTreatment.withdrawalPeriodExpirationDate"></b-form-input>
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
    components: { DatePicker },
    data() {
      return {
        entityManagementNumber: ''
        , entityIdentificationNumber: ''
        , parentPapaNo: ''
        , parentMomNo: ''
        , birthDay: ''
        , enterDate: ''
        , earTagDate: ''
        , castrationDate: ''
        , gender: 'MALE'
        , sellYn: 'N'
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
        , calvesManagement: {}
        , diseaseTreatment: {}
        , time1: '2021-01-01 00:00:00'
      }
    },
    mounted() {

      let actionURL = `${this.$store.state.host}/cattle/detail?`
      console.log(actionURL)
      this.$http.get(actionURL, {
        params: {
          entityManagementNumber: this.$route.query.entityNumber,
          entityIdentificationNumber: this.$route.query.identityNumber
        }
      }).then((res) => {

        console.log('rount response :', res)
        let responseData = res.data.detail.data


        this.entityManagementNumber = responseData.localBeefManagementPK.entityManagementNumber
        this.entityIdentificationNumber = responseData.localBeefManagementPK.entityIdentificationNumber
        this.parentPapaNo = responseData.parentPapaNo
        this.parentMomNo = responseData.parentMomNo
        this.birthDay = responseData.birthDay
        this.enterDate = responseData.enterDate
        this.earTagDate = responseData.earTagDate
        this.castrationDate = responseData.castrationDate
        this.gender = responseData.gender
        this.sellYn = responseData.sellYn

        this.cattleBuyInformation = responseData.cattleBuyInformation
        this.cattleSellStoreInformation = responseData.cattleSellStoreInformation
        this.diseaseTreatmentSet = responseData.diseaseTreatmentSet
        this.calvesManagementSet = responseData.calvesManagementSet

      }).catch((error) => {
        console.log(error.message())
      })
    },
    computed: {},
    methods: {
      cattleModifyForm: function () {
        let actionUrl = `/cattle/cattleForm?entityNumber=${this.entityManagementNumber}&identityNumber=${this.entityIdentificationNumber}`
        this.$router.push(actionUrl)
      }
      , calvesSave: function () {

      }
      , calvesDetail: function (entityNumber, identityNumber, seq) {

        this.$http.get(this.$store.state.host + '/cattle/childbirth/detail', {
          params: {
            entityManagementNumber: entityNumber,
            entityIdentificationNumber: identityNumber,
            seq: seq
          }
        }).then(({data}) => {


          this.calvesManagement = data.detail.data
          this.$refs['calves-modal'].show()


        })

      }, diseaseDetail: function () {

      }, calvesResetModal () {
        this.calvesManagement = {}
      }, diseaseResetModal() {
        this.diseaseTreatment = {}
      }

    }
  }

</script>

<style scoped="scoped">

</style>
