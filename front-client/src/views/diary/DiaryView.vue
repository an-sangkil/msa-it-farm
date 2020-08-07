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
                  <div class="form-group col-md-12 row">
                    <label class="col-md-1 col-form-label">standardDate</label>
                    <div class="col-md-2">
                      <input class="form-control" id="uuid" type="hidden" name="text-input" v-model="uuid">
                      <input class="form-control" id="seq" type="hidden" name="text-input" v-model="seq">
                      <p class="form-control-static">{{standardDate}}</p>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-4 row">
                    <label class="col-md-3 col-form-label">todayWeatherCode</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{todayWeatherCode}}</p>
                    </div>
                  </div>
                  <div class="form-group col-md-4 row">
                    <label class="col-md-3 col-form-label">minimumTemperature</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{minimumTemperature}}</p>
                    </div>
                  </div>
                  <div class="form-group col-md-4 row">
                    <label class="col-md-3 col-form-label">maximumTemperature</label>
                    <div class="col-md-9">
                      <p class="form-control-static">{{maximumTemperature}}</p>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-12 row">
                    <label class="col-md-1 col-form-label">subject</label>
                    <div class="col-md-11">
                      <p class="form-control-static">{{subject}}</p>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-12 row">
                    <label class="col-md-1 col-form-label">content</label>
                    <div class="col-md-11">
                      <p class="form-control-static">{{content}}</p>
                    </div>
                  </div>
                </div>

              </form>
            </div>
            <div class="card-footer">
              <!--<button class="btn btn-sm btn-info" > Submit</button>-->



              <div class="row justify-content-end">

                <div class="col-6 col-sm-4 col-md-2 col-xl mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-info"  type="submit" v-on:click="scheduleModify()">modify</button>
                </div>

                <div class="col-6 col-sm-4 col-md-2 col-xl mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-info"  type="button" v-on:click="scheduleList()">list</button>
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

  export default {
    created() {

      this.$store.commit('changeHome', {name: 'Home'})
      this.$store.commit('changeTitle', {name: 'Schedule'})
      this.$store.commit('changeSubtitle', {name: 'Form'})

    },

    data() {
      return {
        uuid: '',
        seq: '',
        standardDate: '',
        subject: '',
        content: '',
        todayWeatherCode: '',
        minimumTemperature: '',
        maximumTemperature: '',
      }
    },
    mounted() {
      this.scheduleDetailView()

    },
    computed: {},
    methods: {
      scheduleDetailView: function () {
        let actionURL = `${this.$store.state.host}/diary/detail?`

        console.log(actionURL)
        this.$http.get(actionURL, {
          params: {
            uuid: this.$route.query.uuid
            , seq: this.$route.query.seq
          }
        }).then((res) => {

          let scheduleData = res.data.detail.data
          scheduleData = res.data.detail.data
          this.uuid = scheduleData.uuid
          this.seq = scheduleData.seq
          this.standardDate = scheduleData.standardDate
          this.subject = scheduleData.subject
          this.content = scheduleData.content
          this.todayWeatherCode = scheduleData.todayWeatherCode
          this.minimumTemperature = scheduleData.minimumTemperature
          this.maximumTemperature = scheduleData.maximumTemperature

        })
      },
      scheduleModify: function () {
        this.$router.push(`/diary/diaryForm?uuid=${this.uuid}`)
      },
      scheduleList: function () {
        this.$router.push(`/diary/diaryList`)
      }


    }
  }

</script>

<style scoped="scoped">

</style>
