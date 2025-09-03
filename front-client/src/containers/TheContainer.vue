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
import { defineComponent } from 'vue'
import TheSidebar from './TheSidebar.vue'
import TheHeader from './TheHeader.vue'
import TheFooter from './TheFooter.vue'

export default defineComponent({
  name: 'TheContainer',
  components: {
    TheSidebar,
    TheHeader,
    TheFooter
  }
})
</script>


<style scoped>
</style>
