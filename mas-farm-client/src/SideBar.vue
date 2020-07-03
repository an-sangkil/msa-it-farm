
<template>
  <div id="app">
    <hr>
    <template-compoment></template-compoment>
    <hr>
  </div>
</template>

<script>
  import Template from "@/components/Template.vue";

  export default {
    components: {
      'template-compoment': Template
    },
    name: 'SideBar'
  }
</script>


<style>
</style>
