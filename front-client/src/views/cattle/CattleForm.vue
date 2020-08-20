<template>
  <div class="container-fluid">
    <div class="fade-in">
      <div class="row">

        <!-- cattle management (localbeef)  -->
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header"><strong>기본정보</strong>.</div>
            <div class="card-body">
              <form class="form-horizontal" action="" method="post" enctype="multipart/form-data" v-on:submit.prevent="cattleSave">
                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label" for="entityIdentificationNumber1"><span style="color: #ff0000">*</span> identificationNumber</label>
                    <div class="col-md-9">
                      <b-row>
                        <b-col sm="3"><input class="form-control" id="entityIdentificationNumber1" type="number"  placeholder="Text" v-model="identityNumberOne"  v-bind:readonly="isReadonly" min="0" max="999"></b-col>
                        <b-col sm="7"><input class="form-control" id="entityIdentificationNumber2" type="number"  placeholder="Text" v-model="identityNumberTwo"  v-bind:readonly="isReadonly" maxlength="8" min="0" max="99999999" v-on:focusout="entityManagementNumber=identityNumberTwo" ></b-col>
                        <b-col sm="2"><input class="form-control" id="entityIdentificationNumber3" type="number"  placeholder="Text" v-model="identityNumberThree"  v-bind:readonly="isReadonly"  maxlength="1" min="0" max="9"></b-col>
                      </b-row>
                      <span class="help-block">This is a help text</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label"><span style="color: red">*</span> entityNumber</label>
                    <div class="col-md-9">
                      <input class="form-control" id="entityManagementNumber" type="number" name="entityManagementNumber" placeholder="Text" v-model="entityManagementNumber" readonly><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">parentPapaNo</label>
                    <div class="col-md-9">
                      <input class="form-control" id="parentPapaNo" type="number" name="parentPapaNo" placeholder="Text" v-model="parentPapaNo"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">parentMomNo</label>
                    <div class="col-md-9">
                      <input class="form-control" id="parentMomNo" type="number" name="parentMomNo" placeholder="Text" v-model="parentMomNo"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label" >birthDay</label>
                    <div class="col-md-9">
                      <date-picker v-model="birthDay" value-type="format" format="YYYY-MM-DD" type="date" placeholder="Select birthDay" style="width: 100%"></date-picker>
                      <span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label" >enter Date</label>
                    <div class="col-md-9">
                      <date-picker v-model="enterDate" value-type="format" format="YYYY-MM-DD" type="date" placeholder="Select enterDate" style="width: 100%"></date-picker>
                      <span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label" >earTagDate</label>
                    <div class="col-md-9">
                      <date-picker v-model="earTagDate" value-type="format" format="YYYY-MM-DD" type="date" placeholder="Select earTagDate" style="width: 100%"></date-picker>
                      <span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label" >castrationDate</label>
                    <div class="col-md-9">
                      <date-picker v-model="castrationDate" value-type="format" format="YYYY-MM-DD" type="date" placeholder="Select castrationDate" style="width: 100%"></date-picker>
                      <span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                </div>


                <div class="row">

                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">gender</label>
                    <div class="col-md-9 col-form-label">
                      <div class="form-check form-check-inline mr-1">
                        <input class="form-check-input" id="gender1" type="radio" value="MALE" name="gender" v-model="gender">
                        <label class="form-check-label" for="gender1">MALE</label>
                      </div>
                      <div class="form-check form-check-inline mr-1">
                        <input class="form-check-input" id="gender2" type="radio" value="FEMALE" name="gender" v-model="gender">
                        <label class="form-check-label" for="gender2">FEMALE</label>
                      </div>
                    </div>
                  </div>

                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">sellYn</label>
                    <div class="col-md-9">
                      <select class="form-control" id="sellYn" name="sellYn" v-model="sellYn">
                        <option value="Y">Option Y</option>
                        <option value="N">Option N</option>
                      </select>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label" for="roomNumber">roomNumber</label>
                    <div class="col-md-9">
                      <b-input v-model="roomNumber" id="roomNumber"></b-input>
                      <span class="help-block">Please enter a valid roomNumber</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">

                  </div>
                </div>


              </form>

            </div>
            <div class="card-footer">
              <div class="row justify-content-end">
                <div class="col-6 col-sm-4 col-md-2 col-xl mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-info" type="button" v-on:click="$router.go(-1)">cancel</button>
                </div>
                <div class="col-6 col-sm-4 col-md-2 col-xl mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-info" type="button" v-on:click="cattleSave($event)">submit</button>
                </div>
              </div>
            </div>
          </div>
        </div>


        <!-- buy information -->
        <div class="col-lg-12" v-if="entityIdentificationNumber != ''">
          <div class="card">
            <div class="card-header"><strong>구입 정보</strong>.</div>
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
                    <date-picker v-model="cattleBuyInformation.buyDate" value-type="format" format="YYYY-MM-DD" type="date" placeholder="buyDate"></date-picker>
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
        <div class="col-lg-12" v-if="entityIdentificationNumber != ''">
          <div class="card">
            <div class="card-header"><strong>판매정보</strong>.</div>
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
                    <date-picker v-model="cattleSellStoreInformation.sellDate" value-type="format" format="YYYY-MM-DD" type="date" placeholder="sellDate" style="width: 100%"></date-picker>
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
            <!--<div class="card-footer">
            </div>-->
          </div>
        </div>

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
      this.$store.commit('changeSubtitle', {name: 'Form'})

    },
    components:{
      DatePicker
    },
    data() {
      return {

        identityNumberOne : ''
        , identityNumberTwo : ''
        , identityNumberThree : ''

        , entityManagementNumber: ''
        , entityIdentificationNumber: ''
        , parentPapaNo: ''
        , parentMomNo: ''
        , birthDay: ''
        , enterDate: ''
        , earTagDate: ''
        , castrationDate: ''
        , roomNumber:''
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
        , isReadonly:false
      }
    },
    mounted() {

      let entityManagementNumber =  this.$route.query.entityNumber
      let entityIdentificationNumber =  this.$route.query.identityNumber

      if (entityManagementNumber !== undefined) {

        this.detail(entityManagementNumber,entityIdentificationNumber)
        this.isReadonly = true
      }

    },
    computed: {},
    methods: {
      validation :function(){

        if ( this.entityIdentificationNumber == '' || this.entityIdentificationNumber == '') {
          console.log('필수 입력 데이터 입니다.')
          return false
        } else {
          return true
        }

      },
      cattleSave: function (e) {

        let data = this.dataGeneration()

        if (!this.validation()){
          console.log("여기?")
          return false;
        }

        this.$http.put(this.$store.state.HOST + '/cattle/save', data)
          .then((res) => {

            console.log(res)
            if (res.data.status === "SUCCESS") {
              let entityNumber = res.data.detail.contents.localBeefManagementPK.entityManagementNumber
              let identityNumber = res.data.detail.contents.localBeefManagementPK.entityIdentificationNumber
              this.$router.push(`/cattle/cattleDetail?entityNumber=${entityNumber}&identityNumber=${identityNumber}`)
              this.$store.commit("showAlert", {message: 'save success', variant: 'success'})
            }

          }).catch((error) => {
          console.log(error)
        })

      },
      dataGeneration: function () {

        let identity = [this.identityNumberOne , this.identityNumberTwo, this.identityNumberThree]
        this.entityIdentificationNumber = identity.join('')
        this.entityManagementNumber = this.identityNumberTwo

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
            , roomNumber: this.roomNumber
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
        return data

      },
      detail :function (entityNumber, identityNumber) {
        let actionURL = `${this.$store.state.HOST}/cattle/detail?`

        this.$http.get(actionURL, {
          params: {
            entityManagementNumber: entityNumber,
            entityIdentificationNumber: identityNumber
          }
        }).then((res) => {

          console.log(res)
          let responseData = res.data.detail.contents

          this.entityIdentificationNumber = responseData.localBeefManagementPK.entityIdentificationNumber
          this.identityNumberOne = this.entityIdentificationNumber.substring(0,3)
          this.identityNumberTwo = this.entityIdentificationNumber.substring(3,11)
          this.identityNumberThree = this.entityIdentificationNumber.substring(11)

          this.entityManagementNumber = responseData.localBeefManagementPK.entityManagementNumber

          this.parentPapaNo = responseData.parentPapaNo
          this.parentMomNo = responseData.parentMomNo
          this.birthDay = responseData.birthDay
          this.enterDate = responseData.enterDate
          this.earTagDate = responseData.earTagDate
          this.castrationDate = responseData.castrationDate
          this.gender = responseData.gender
          this.sellYn = responseData.sellYn
          this.roomNumber = responseData.roomNumber

          this.cattleBuyInformation = responseData.cattleBuyInformation
          this.cattleSellStoreInformation = responseData.cattleSellStoreInformation
          this.diseaseTreatmentSet = responseData.diseaseTreatmentSet
          this.calvesManagementSet = responseData.calvesManagementSet

          console.log( responseData.calvesManagementSet)

        }).catch((error) => {
          console.log(error.message)
        })

      }
    }
  }

</script>

<style scoped="scoped">

</style>
