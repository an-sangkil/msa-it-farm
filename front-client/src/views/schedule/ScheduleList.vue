<template>
  <div class="container-fluid">
    <div class="fade-in">
      <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header">
              <div class="row align-items-center">
                <i class="fa fa-align-justify"></i>
                <div class="col-12 col-xl mb-3 mb-xl-0">search</div>
                <div class=" mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-success" type="button" v-on:click="scheduleForm()">register</button>
                </div>
              </div>
            </div>

            <div class="card-body">
            </div>
          </div>
        </div>




      </div>

      <div class="row" v-for="item in scheduleData" >
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header"><i class="fa fa-align-justify"></i>
              <input class="form-control" id="uuid" type="hidden" name="text-input" v-model="item.uuid">
              <input class="form-control" id="seq" type="hidden" name="text-input" v-model="item.seq">
              <h2 style="cursor: pointer" v-on:click="scheduleDetail(item.uuid,item.seq)">{{item.standardDate}} ({{item.seq}})</h2>
            </div>
            <div class="card-body">

              <div class="row">
                <div class="form-group col-md-4 row">
                  <label class="col-md-3 col-form-label">standardDate</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.standardDate}}</p>
                  </div>
                </div>
                <div class="form-group col-md-4 row" style="visibility: hidden">
                  <label class="col-md-3 col-form-label"  >seq</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.seq}}</p>
                  </div>
                </div>
                <div class="form-group col-md-4 row" >
                  <label class="col-md-3 col-form-label" >createdTime</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.createdTime}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-4 row">
                  <label class="col-md-3 col-form-label">todayWeatherCode</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.todayWeatherCode}}</p>
                  </div>
                </div>
                <div class="form-group col-md-4 row" >
                  <label class="col-md-3 col-form-label" >minimumTemperature</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.minimumTemperature}}</p>
                  </div>
                </div>
                <div class="form-group col-md-4 row" >
                  <label class="col-md-3 col-form-label" >maximumTemperature</label>
                  <div class="col-md-9">
                    <p class="form-control-static">{{item.maximumTemperature}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-12 row" >
                  <label class="col-md-1 col-form-label">subject</label>
                  <div class="col-md-11">
                    <p class="form-control-static">{{item.subject}}</p>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group col-md-12 row">
                  <label class="col-md-1 col-form-label">content</label>
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
  export default {
    created() {

      this.$store.commit('changeHome', {name: 'Home'})
      this.$store.commit('changeTitle', {name: 'Schedule'})
      this.$store.commit('changeSubtitle', {name: 'List'})

    },

    data() {
      return {
        startDate: '',
        endDate: '',
        scheduleData: []
      }
    },
    mounted() {
      this.scheduleDataSearch()

    },
    computed: {},
    methods: {
      scheduleForm: function () {

        this.$router.push("/schedule/scheduleForm");

      },
      scheduleDataSearch: function (startDate, endDate) {
        this.$http.get(this.$store.state.host + '/schedule/list', {
          params: {
            // start_date: '2020-02-02',
            // end_date: '2020-02-03'
          }
        })
          .then((res) => {
            console.log(res.data)
            this.scheduleData = res.data.detail.data

          }).catch((error) => {
          console.log(error)
        })
      },
      scheduleDetail:function(uuid, seq) {
        let actionURL = `/schedule/scheduleDetail?uuid=${uuid}&seq=${seq}`
        this.$router.push(actionURL)

      }
    }
  }
</script>

<style>

</style>
