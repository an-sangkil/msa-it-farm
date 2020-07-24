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


        <div class="col-lg-12">
          <div class="card">
            <div class="card-header"><i class="fa fa-align-justify"></i>
              header schedule
            </div>
            <div class="card-body">
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
      return {}
    },
    mounted() {

    },
    computed:{

    },
    methods: {
      cattleSave: function () {

      },
      scheduleForm: function () {

        this.$router.push("/schedule/scheduleForm");

      }

    }
  }
</script>

<style>

</style>
