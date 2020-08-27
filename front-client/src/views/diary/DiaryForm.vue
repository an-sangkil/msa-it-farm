<template>
  <div class="container-fluid">
    <div class="fade-in">
      <div class="row">
        <div class="col-lg-12">


          <div class="card">
            <div class="card-header"><strong>Basic Form</strong> Elements</div>
            <div class="card-body">
              <form class="form-horizontal" action="" method="post" enctype="multipart/form-data">

                <div class="row">
                  <div class="form-group col-md-4 row">
                    <label class="col-md-3 col-form-label">날짜</label>
                    <div class="col-md-9">
                      <input class="form-control" id="uuid" type="hidden" name="text-input" v-model="uuid">
                      <input class="form-control" id="seq" type="hidden" name="text-input" v-model="seq">
                      <date-picker v-model="standardDate" value-type="format" format="YYYY-MM-DD" type="date" placeholder="standardDate" style="width: 100%"></date-picker>
                      <span class="help-block">This is a standardDate text</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-4 row">
                    <label class="col-md-3 col-form-label">날씨</label>
                    <div class="col-md-9">
                      <b-form-select v-model="todayWeatherCode" :options="weatherOptions"></b-form-select>
                      <span class="help-block">This is a weather Options </span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-4 row">
                    <label class="col-md-3 col-form-label">최저온도</label>
                    <div class="col-md-9 row">
                      <div class="col-md-6">
                        <b-form-select v-model="minTemperatureType" :options="temperatureOption"></b-form-select>
                        <span class="help-block"></span>
                      </div>
                      <div class="col-md-6">
                        <input class="form-control" type="number" name="date-input" placeholder="minimumTemperature" v-model="minimumTemperature">
                        <span class="help-block"></span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group col-md-4 row">
                    <label class="col-md-3 col-form-label">최고온도</label>
                    <div class="col-md-9 row">
                      <div class="col-md-6">
                        <b-form-select v-model="maxTemperatureType" :options="temperatureOption"></b-form-select>
                        <span class="help-block"></span>
                      </div>
                      <div class="col-md-6">
                        <input class="form-control" type="number" name="date-input" placeholder="maximumTemperature" v-model="maximumTemperature">
                        <span class="help-block"></span>
                      </div>
                    </div>
                  </div>
                </div>


                <div class="row">
                  <div class="form-group col-md-12 row">
                    <label class="col-md-1 col-form-label">제목</label>
                    <div class="col-md-11">
                      <input class="form-control" id="text-input1" type="text" name="text-input" placeholder="subject" v-model="subject">
                      <span class="help-block">This is a help text</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-12 row">
                    <label class="col-md-1 col-form-label">내용</label>
                    <div class="col-md-11">
                      <textarea class="form-control" id="textarea-input" name="textarea-input" rows="9" placeholder="Content.." v-model="content"></textarea>
                    </div>
                  </div>
                </div>

              </form>
            </div>
            <div class="card-footer">
              <!--<button class="btn btn-sm btn-info" > Submit</button>-->
              <div class="row justify-content-end">
                <div class="col-6 col-sm-4 col-md-2 col-xl mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-info" type="button" v-on:click="$router.go(-1)">취소</button>
                </div>
                <div class="col-6 col-sm-4 col-md-2 col-xl mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-info" type="button" v-on:click="scheduleSave()">저장</button>
                </div>
              </div>
              <!--<button class="btn btn-sm btn-danger" type="reset"> Reset</button>-->
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

  export default {
    created() {

      this.$store.commit('changeHome', {name: 'Home'})
      this.$store.commit('changeTitle', {name: 'Schedule'})
      this.$store.commit('changeSubtitle', {name: 'Form'})

    },
    components: {
      DatePicker
    }, data() {
      return {
        uuid: '',
        seq: '',
        standardDate: '',
        subject: '',
        content: '',
        todayWeatherCode: '',
        minTemperatureType: '',
        maxTemperatureType: '',
        minimumTemperature: '',
        maximumTemperature: '',
        weatherOptions: [
          {value: '', text: '선택하세요'},
          {value: 'SUNNY', text: '맑음'},
          {value: 'CLOUDY', text: '흐림'}
        ],
        temperatureOption: [
          {value: '', text: '선택하세요'},
          {value: 'PLUS_DEGREES', text: '영상'},
          {value: 'MINUS_DEGREES', text: '영하'}
        ],

      }
    },
    mounted() {
      let uuid = this.$route.query.uuid
      if (uuid !== undefined) {
        this.scheduleDetailView()
      }
    },
    computed: {},
    methods: {
      scheduleSave: function () {

        let data = JSON.stringify({
          uuid: this.uuid,
          seq: this.seq,
          standardDate: this.standardDate,
          subject: this.subject,
          content: this.content,
          todayWeatherCode: this.todayWeatherCode,
          minTemperatureType: this.minTemperatureType,
          maxTemperatureType: this.maxTemperatureType,
          minimumTemperature: this.minimumTemperature,
          maximumTemperature: this.maximumTemperature
        })

        console.log(data)

        this.$http.put(this.$store.state.HOST + '/diary/save', data, {
          headers: {"Content-Type": "application/json"}
        })
          .then((res) => {
            console.log(res.data)
            if (res.data.status === "SUCCESS") {

              let uuid = res.data.detail.contents.uuid
              let seq = res.data.detail.contents.seq

              this.$store.commit("showAlert", {message: 'save success', variant: 'success'})

              let actionURL = `/diary/diaryDetail?uuid=${uuid}&seq=${seq}`
              this.$router.push(actionURL)

            }

          }).catch((error) => {
          console.log(error)
        })


      }, scheduleDetailView: function () {
        let actionURL = `${this.$store.state.HOST}/diary/detail?`

        console.log(actionURL)
        this.$http.get(actionURL, {
          params: {
            uuid: this.$route.query.uuid
            , seq: this.$route.query.seq
          }
        }).then((res) => {

          let scheduleData = res.data.detail.contents
          this.uuid = scheduleData.uuid
          this.seq = scheduleData.seq
          this.standardDate = scheduleData.standardDate
          this.subject = scheduleData.subject
          this.content = scheduleData.content
          this.todayWeatherCode = scheduleData.todayWeatherCode
          this.minTemperatureType = scheduleData.minTemperatureType,
          this.maxTemperatureType = scheduleData.maxTemperatureType,

          this.minimumTemperature = scheduleData.minimumTemperature
          this.maximumTemperature = scheduleData.maximumTemperature

        })
      }
    }
  }

</script>

<style scoped="scoped">

</style>
