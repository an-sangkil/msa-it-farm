<template>
  <div class="container-fluid d-flex align-items-center min-vh-100">
    <div class="row w-100 justify-content-center">
      <div class="col-md-6">
        <div class="w-100">
          <div class="clearfix">
            <h1 class="float-left display-3 mr-4">404</h1>
            <h4 class="pt-3">Oops! You're lost.</h4>
            <p class="text-muted">The page you are looking for was not found.</p>
          </div>
          <div class="input-group mb-3">
            <input class="form-control" placeholder="What are you looking for?" />
            <div class="input-group-append">
              <button class="btn btn-info" type="button">Search</button>
            </div>
          </div>
          <router-link to="/" class="btn btn-primary">Go Home</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'Page404'
})
</script>
