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
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">Static0</label>
                    <div class="col-md-9">
                      <input class="form-control" id="text-input0" type="text" name="text-input" placeholder="Text"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row" >
                    <label class="col-md-3 col-form-label">Static1</label>
                    <div class="col-md-9">
                      <input class="form-control" id="text-input1" type="text" name="text-input" placeholder="Text"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">Static2</label>
                    <div class="col-md-9">
                      <input class="form-control" id="text-input3" type="text" name="text-input" placeholder="Text"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row">
                    <label class="col-md-3 col-form-label">Static3</label>
                    <div class="col-md-9">
                      <input class="form-control" id="text-input4" type="text" name="text-input" placeholder="Text"><span class="help-block">This is a help text</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row" >
                    <label class="col-md-3 col-form-label" for="date-input11">Date Input</label>
                    <div class="col-md-9">
                      <input class="form-control" id="date-input11" type="date" name="date-input" placeholder="date"><span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row" >
                    <label class="col-md-3 col-form-label" for="date-input22">Date Input</label>
                    <div class="col-md-9">
                      <input class="form-control" id="date-input22" type="date" name="date-input" placeholder="date"><span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-md-6 row" >
                    <label class="col-md-3 col-form-label" for="date-input33">Date Input</label>
                    <div class="col-md-9">
                      <input class="form-control" id="date-input33" type="date" name="date-input" placeholder="date"><span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                  <div class="form-group col-md-6 row" >
                    <label class="col-md-3 col-form-label" for="date-input44">Date Input</label>
                    <div class="col-md-9">
                      <input class="form-control" id="date-input44" type="date" name="date-input" placeholder="date"><span class="help-block">Please enter a valid date</span>
                    </div>
                  </div>
                </div>


                <div class="row">

                  <div class="form-group col-md-6 row" >
                    <label class="col-md-3 col-form-label">Inline Radios</label>
                    <div class="col-md-9 col-form-label">
                      <div class="form-check form-check-inline mr-1">
                        <input class="form-check-input" id="inline-radio1" type="radio" value="MALE" name="inline-radios">
                        <label class="form-check-label" for="inline-radio1">One M</label>
                      </div>
                      <div class="form-check form-check-inline mr-1">
                        <input class="form-check-input" id="inline-radio2" type="radio" value="FEMALE" name="inline-radios">
                        <label class="form-check-label" for="inline-radio2">Two F</label>
                      </div>
                    </div>
                  </div>

                  <div class="form-group col-md-6 row" >
                    <label class="col-md-3 col-form-label" for="select1">Select</label>
                    <div class="col-md-9">
                      <select class="form-control" id="select1" name="select1">
                        <option value="Y">Option Y</option>
                        <option value="N">Option N</option>
                      </select>
                    </div>
                  </div>
                </div>
              </form>
            </div>
            <div class="card-footer">
              <button class="btn btn-sm btn-primary" type="submit" v-on:submit.prevent="scheduleSave()"> Submit</button>
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
      return {}
    },
    mounted() {

    },
    computed:{

    },
    methods: {
      scheduleSave: function () {

      },
      pageMove: function (currentPage) {


      }

    }
  }

</script>

<style scoped="scoped">

</style>
