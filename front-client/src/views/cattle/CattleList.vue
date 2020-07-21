<template>
  <div>
    cattle list view

    <button v-on:click="cattleSave">add</button>

  </div>
</template>
<script>

  export default {

    methods:{
      cattleSave : function () {
        location.href = "/cattle/cattleSave"
      }
    }
  }

</script>

<style scoped="scoped">

</style>
