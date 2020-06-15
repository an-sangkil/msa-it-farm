<template>
  <div>
    <router-view> </router-view>
<!--    <router-view name="insideVue"> </router-view>-->
  </div>
</template>

<script>
  import Main from "./main/Main";
  export default {
    components : {
      'main-tag' : Main
    },

  }
</script>

<style scoped>

</style>
