<template>
  <div>
    cattle save view

  </div>
</template>
<script>
  import PaginationUtils from '../../js/Pagination'

  export default {
    created() {

      this.$store.commit('changeHome', {name: 'Home'})
      this.$store.commit('changeTitle', {name: 'Cattle'})
      this.$store.commit('changeSubtitle', {name: 'save'})

    },

    data() {
      return {}
    },
    mounted() {
      this.$http.get(this.$store.state.host + '/cattle/cattle_management_list')
        .then((res) => {
          //console.log("response = ",res)
          this.pagingProcess(res);
        })
        .catch((error) => {

        })
    },

    methods: {
      cattleSave: function () {

      },
      pageMove: function (currentPage) {


      }
      ,
      pagingProcess: function (res) {

      }
    }
  }

</script>

<style scoped="scoped">

</style>
