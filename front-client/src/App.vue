<script src="containers/_nav.js"></script>
<script src="main.js"></script>
<template>
  <router-view></router-view>
</template>


<script>
  export default {
    name: 'App'
  }

  // coreui 모듈 사용하지 않음
  require('./vendors/@coreui/coreui/js/coreui.bundle.min.js')
  require('./vendors/@coreui/icons/js/svgxuse.min.js')
  //require('./vendors/@coreui/chartjs/js/coreui-chartjs.bundle.js')
  require('./vendors/@coreui/utils/js/coreui-utils.js')


</script>

<style>
  /* main.js 에서 링크 됨 여기는 주석처리.*/
  /*@import './css/style.css';*/
</style>
