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
                      <input class="form-control" id="text-input0" type="date" name="text-input" placeholder="Text" v-model="standardDate"><span class="help-block">This is a help text</span>

                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-4 row">
                    <label class="col-md-3 col-form-label">todayWeatherCode</label>
                    <div class="col-md-9">
                      <input class="form-control" id="text-input4" type="text" name="text-input" placeholder="Text" v-model="todayWeatherCode"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                  <div class="form-group col-md-4 row">
                    <label class="col-md-3 col-form-label">minimumTemperature</label>
                    <div class="col-md-9">
                      <input class="form-control" type="text" name="date-input" placeholder="date" v-model="minimumTemperature"><span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                  <div class="form-group col-md-4 row">
                    <label class="col-md-3 col-form-label">maximumTemperature</label>
                    <div class="col-md-9">
                      <input class="form-control" type="text" name="date-input" placeholder="date" v-model="maximumTemperature"><span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-12 row">
                    <label class="col-md-1 col-form-label">subject</label>
                    <div class="col-md-11">
                      <input class="form-control" id="text-input1" type="text" name="text-input" placeholder="Text" v-model="subject"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-12 row">
                    <label class="col-md-1 col-form-label">content</label>
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
                <div class="mb-3 mb-xl-0">
                  <button class="btn btn-block btn-info" type="submit" v-on:click="scheduleSave()">submit</button>
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
          minimumTemperature: this.minimumTemperature,
          maximumTemperature: this.maximumTemperature
        })

        console.log(data)

        this.$http.put(this.$store.state.host + '/schedule/save', data, {
          headers: {"Content-Type": "application/json"}
        })
          .then((res) => {
            console.log(res.data)
            if (res.data.status === "SUCCESS") {

              let uuid = res.data.detail.data.uuid
              let seq = res.data.detail.data.seq

              this.$store.commit("showAlert", {message: 'save success', variant: 'success'})

              let actionURL = `/schedule/scheduleDetail?uuid=${uuid}&seq=${seq}`
              this.$router.push(actionURL)

            }

          }).catch((error) => {
          console.log(error)
        })


      }, scheduleDetailView: function () {
        let actionURL = `${this.$store.state.host}/schedule/detail?`

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
      }
    }
  }

</script>

<style scoped="scoped">

</style>
