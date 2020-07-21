<template>
  <div class="c-app">
    <TheSidebar></TheSidebar>
    <div class="c-wrapper c-fixed-components">
      <TheHeader></TheHeader>

      <div class="c-body">
        <main class="c-main">
          <router-view></router-view>
        </main>
      </div>
      <TheFooter></TheFooter>
    </div>
  </div>
</template>


<script>
  import TheSidebar from './TheSidebar'
  import TheHeader from './TheHeader'
  import TheFooter from './TheFooter'

  export default {
    name: 'TheContainer',
    components: {
      TheSidebar,
      TheHeader,
      TheFooter
    }
  }
</script>


<style scoped>
</style>
