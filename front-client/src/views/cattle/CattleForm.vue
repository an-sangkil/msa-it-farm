<template>
  <div class="container-fluid">
    <div class="fade-in">
      <div class="row">
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
            <div class="card-footer">
              <button class="btn btn-sm btn-primary" type="submit" v-on:click="cattleSave($event)"> Submit</button>
              <!--<button class="btn btn-sm btn-danger" type="reset"> Reset</button>-->
            </div>
          </div>


        </div>
      </div>
    </div>
  </div>
</template>
<script>

  import * as qs from "javascript-stringify";

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
      }
    },
    mounted() {

    },
    computed: {},
    methods: {
      cattleSave: function (e) {

        console.log(this.gender)
        console.log(this.sellYn)
        let data = qs.stringify({
          entityManagementNumber: this.entityIdentificationNumber
          , entityIdentificationNumber: this.entityIdentificationNumber
          , parentPapaNo: this.parentPapaNo
          , parentMomNo: this.parentMomNo
          , birthDay: this.birthDay
          , enterDate: this.enterDate
          , earTagDate: this.earTagDate
          , castrationDate: this.castrationDate
          , gender: this.gender
          , sellYn: this.sellYn
        })

        console.log(data)

        this.$http.put(this.$store.state.host + '/cattle/save', data)
          .then((res) => {
            console.log(res.data)
          }).catch((error) => {
          console.log(error)
        })

      },
      pageMove: function (currentPage) {


      }
    }
  }

</script>

<style scoped="scoped">

</style>
