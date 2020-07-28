<template>
  <div class="container-fluid">
    <div class="fade-in">
      <div class="row">

        <!-- cattle management (localbeef)  -->
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header"><strong>Basic Form</strong> Elements</div>
            <div class="card-body">
              <form class="form-horizontal" action="" method="post" enctype="multipart/form-data" v-on:submit.prevent="cattleSave">
                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">Static0</label>
                    <div class="col-md-9">
                      <input class="form-control" id="entityManagementNumber" type="text" name="entityManagementNumber" placeholder="Text" v-model="entityManagementNumber"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">Static1</label>
                    <div class="col-md-9">
                      <input class="form-control" id="entityIdentificationNumber" type="text" name="entityIdentificationNumber" placeholder="Text" v-model="entityIdentificationNumber"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">Static2</label>
                    <div class="col-md-9">
                      <input class="form-control" id="parentPapaNo" type="text" name="parentPapaNo" placeholder="Text" v-model="parentPapaNo"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">Static3</label>
                    <div class="col-md-9">
                      <input class="form-control" id="parentMomNo" type="text" name="parentMomNo" placeholder="Text" v-model="parentMomNo"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label" for="birthDay">Date Input</label>
                    <div class="col-md-9">
                      <input class="form-control" id="birthDay" type="date" name="birthDay" placeholder="date" v-model="birthDay"><span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label" for="enterDate">enter Date</label>
                    <div class="col-md-9">
                      <input class="form-control" id="enterDate" type="date" name="enterDate" placeholder="date" v-model="enterDate"><span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label" for="earTagDate">Date Input</label>
                    <div class="col-md-9">
                      <input class="form-control" id="earTagDate" type="date" name="earTagDate" placeholder="date" v-model="earTagDate"><span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label" for="castrationDate">Date Input</label>
                    <div class="col-md-9">
                      <input class="form-control" id="castrationDate" type="date" name="castrationDate" placeholder="date" v-model="castrationDate"><span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                </div>


                <div class="row">

                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">Inline Radios</label>
                    <div class="col-md-9 col-form-label">
                      <div class="form-check form-check-inline mr-1">
                        <input class="form-check-input" id="gender1" type="radio" value="MALE" name="gender" v-model="gender">
                        <label class="form-check-label" for="gender1">One M</label>
                      </div>
                      <div class="form-check form-check-inline mr-1">
                        <input class="form-check-input" id="gender2" type="radio" value="FEMALE" name="gender" v-model="gender">
                        <label class="form-check-label" for="gender2">Two F</label>
                      </div>
                    </div>
                  </div>

                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">Select</label>
                    <div class="col-md-9">
                      <select class="form-control" id="sellYn" name="sellYn" v-model="sellYn">
                        <option value="Y">Option Y</option>
                        <option value="N">Option N</option>
                      </select>
                    </div>
                  </div>
                </div>
              </form>

            </div>
            <!--<div class="card-footer">
              <button class="btn btn-sm btn-danger" type="reset"> Reset</button>
            </div>-->
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
                    <input class="form-control" type="text" v-model="cattleBuyInformation.buyStoreName">
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">buyDate</label>
                  <div class="col-md-9">
                    <input class="form-control" type="date" v-model="cattleBuyInformation.buyDate">
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">buySellPhoneNumber</label>
                  <div class="col-md-9">
                    <input class="form-control" type="text" v-model="cattleBuyInformation.buySellPhoneNumber">
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">buyPhoneNumber</label>
                  <div class="col-md-9">
                    <input class="form-control" type="text" v-model="cattleBuyInformation.buyPhoneNumber">
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-12 row">
                  <label class="col-md-2 col-form-label">buyNote</label>
                  <div class="col-md-10">
                    <input class="form-control" type="text" v-model="cattleBuyInformation.buyNote">
                  </div>
                </div>
              </div>

            </div>
            <!--<div class="card-footer">
              <button class="btn btn-sm btn-primary" type="submit"> Submit</button>
              <button class="btn btn-sm btn-danger" type="reset"> Reset</button>
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
                    <input class="form-control" type="text" v-model="cattleSellStoreInformation.storeName">
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">sellDate</label>
                  <div class="col-md-9">
                    <input class="form-control" type="date" v-model="cattleSellStoreInformation.sellDate">
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">sellNote</label>
                  <div class="col-md-9">
                    <input class="form-control" type="text" v-model="cattleSellStoreInformation.sellNote">
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">sellPhoneNumber</label>
                  <div class="col-md-9">
                    <input class="form-control" type="text" v-model="cattleSellStoreInformation.sellPhoneNumber">
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">sellingPrice</label>
                  <div class="col-md-9">
                    <input class="form-control" type="number" v-model="cattleSellStoreInformation.sellingPrice">
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">liveweight</label>
                  <div class="col-md-9">
                    <input class="form-control" type="number" v-model="cattleSellStoreInformation.liveweight">
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">dressedWeight</label>
                  <div class="col-md-9">
                    <input class="form-control" type="number" v-model="cattleSellStoreInformation.dressedWeight">
                  </div>
                </div>
                <div class="form-group col-md-6 row">
                  <label class="col-md-3 col-form-label">beefGrade</label>
                  <div class="col-md-9">
                    <input class="form-control" type="text" v-model="cattleSellStoreInformation.beefGrade">
                  </div>
                </div>
              </div>


            </div>
            <div class="card-footer">
              <div class="row align-items-center">
                <div class="mb-3 mb-xl-0">
                  <button class="btn btn-block btn-info" type="submit" v-on:click="cattleSave($event)">submit</button>
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


  export default {
    created() {

      this.$store.commit('changeHome', {name: 'Home'})
      this.$store.commit('changeTitle', {name: 'Cattle'})
      this.$store.commit('changeSubtitle', {name: 'Form'})

    },

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
        , diseaseTreatmentSet: []
        , calvesManagementSet: []
      }
    },
    mounted() {

      let entityManagementNumber =  this.$route.query.entityNumber
      let entityIdentificationNumber =  this.$route.query.identityNumber

      if (entityManagementNumber !== undefined)
        this.detail(entityManagementNumber,entityIdentificationNumber)
    },
    computed: {},
    methods: {
      validation :function(){

      },
      cattleSave: function (e) {


        let data = JSON.stringify({
            localBeefManagementPK: {
              entityManagementNumber: this.entityManagementNumber
              , entityIdentificationNumber: this.entityIdentificationNumber
            },
            parentPapaNo: this.parentPapaNo
            , parentMomNo: this.parentMomNo
            , birthDay: this.birthDay
            , enterDate: this.enterDate
            , earTagDate: this.earTagDate
            , castrationDate: this.castrationDate
            , gender: this.gender
            , sellYn: this.sellYn
            , cattleBuyInformation: {
              buyStoreName: this.cattleBuyInformation.buyStoreName
              , buyDate: this.cattleBuyInformation.buyDate
              , buyNote: this.cattleBuyInformation.buyNote
              , buyPhoneNumber: this.cattleBuyInformation.buyPhoneNumber
              , buySellPhoneNumber: this.cattleBuyInformation.buySellPhoneNumber
            }
            , cattleSellStoreInformation: {
              storeName: this.cattleSellStoreInformation.storeName
              , sellDate: this.cattleSellStoreInformation.sellDate
              , sellNote: this.cattleSellStoreInformation.sellNote
              , sellPhoneNumber: this.cattleSellStoreInformation.sellPhoneNumber
              , sellingPrice: this.cattleSellStoreInformation.sellingPrice
              , liveweight: this.cattleSellStoreInformation.liveweight
              , dressedWeight: this.cattleSellStoreInformation.dressedWeight
              , beefGrade: this.cattleSellStoreInformation.beefGrade
            }
            , diseaseTreatmentSet: []
            , calvesManagementSet: []
          }
        )

        console.log(data)

        this.$http.put(this.$store.state.host + '/cattle/save', data)
          .then((res) => {
            console.log(res.data)
          }).catch((error) => {
          console.log(error)
        })

      },
      pageMove: function (currentPage) {


      },
      detail :function (entityNumber, identityNumber) {
        let actionURL = `${this.$store.state.host}/cattle/detail?`
        console.log(actionURL)
        this.$http.get(actionURL, {
          params: {
            entityManagementNumber: entityNumber,
            entityIdentificationNumber: identityNumber
          }
        }).then((res) => {

          console.log(res)
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

          console.log( responseData.calvesManagementSet)

        }).catch((error) => {
          console.log(error.message())
        })

      }
    }
  }

</script>

<style scoped="scoped">

</style>
