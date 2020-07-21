<template>
  <div class="c-app flex-row align-items-center">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-8">
          <div class="card-group">
            <div class="card p-4">
              <div class="card-body">
                <h1>Login</h1>
                <p class="text-muted">Sign In to your account</p>
                <div class="input-group mb-3">
                  <div class="input-group-prepend"><span class="input-group-text">
                      <svg class="c-icon">
                        <use xlink:href="../../vendors/@coreui/icons/svg/free.svg#cil-user"></use>
                      </svg></span></div>
                  <input class="form-control" type="text" placeholder="Username">
                </div>
                <div class="input-group mb-4">
                  <div class="input-group-prepend"><span class="input-group-text">
                      <svg class="c-icon">
                        <use xlink:href="../../vendors/@coreui/icons/svg/free.svg#cil-lock-locked"></use>
                      </svg></span></div>
                  <input class="form-control" type="password" placeholder="Password">
                </div>
                <div class="row">
                  <div class="col-6">
                    <button class="btn btn-primary px-4" type="button" v-on:click="login_action">Login</button>
                  </div>
                  <div class="col-6 text-right">
                    <button class="btn btn-link px-0" type="button">Forgot password?</button>
                  </div>
                </div>
              </div>
            </div>
            <div class="card text-white bg-primary py-5 d-md-down-none" style="width:44%">
              <div class="card-body text-center">
                <div>
                  <h2>Sign up</h2>
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                  <button class="btn btn-lg btn-outline-light mt-3" type="button">Register Now!</button>
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

// import('../../vendors/@coreui/coreui/js/coreui.bundle.min.js')
// import('../../vendors/@coreui/icons/js/svgxuse.min.js')

export default {
  name: 'Login',
  methods:{
    login_action : function () {
      this.$http.get("http://localhost:8000/cattle/cattle_management_list").then((res)=>{
        console.log('data =', res.data)

      });

      //location.href='/dashboard'
    }
  }
}
</script>
<style>
  /*@import "../../css/style.css";*/
</style>


