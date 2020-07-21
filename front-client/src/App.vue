<script src="containers/_nav.js"></script>
<script src="main.js"></script>
<template>
  <router-view></router-view>
</template>


<script>
  export default {
    name: 'App'
  }

  require('./vendors/@coreui/coreui/js/coreui.bundle.min.js')
  require('./vendors/@coreui/icons/js/svgxuse.min.js')
  //require('./vendors/@coreui/chartjs/js/coreui-chartjs.bundle.js')
  require('./vendors/@coreui/utils/js/coreui-utils.js')


</script>

<style>
  @import './css/style.css';
</style>
