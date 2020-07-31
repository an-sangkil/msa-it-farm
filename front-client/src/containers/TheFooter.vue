<template>
  <footer class="c-footer">
    <div><a href="https://coreui.io">CoreUI</a> © {{new Date().getFullYear()}} creativeLabs.</div>
    <div class="ml-auto">Powered by&nbsp;<a href="https://coreui.io/">CoreUI</a></div>


    <!-- save alert -->
    <div>
      <b-alert
        :show="$store.state.DISMISS_COUNT_DOWN"
        dismissible
        :variant="$store.state.VARIANT"
        @dismissed="$store.state.DISMISS_COUNT_DOWN=0"
        class="position-fixed fixed-bottom m-0 rounded-0"
        style="z-index: 2000;"
      >
        <p> {{$store.state.MESSAGE}}</p>
      </b-alert>
    </div>
  </footer>
</template>

<script>
  export default {
    name: 'TheFooter',

    data() {
      return {

      }
    },
    mounted() {
      console.log("$store.state.dismissCountDown", this.$store.state.dismissCountDown)
    },
    methods: {

    }
  }
</script>
