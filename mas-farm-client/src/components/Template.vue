

<template>
  <div>
    <t-header></t-header>
    <t-sidebar></t-sidebar>
    <t-body></t-body>
    <t-control-sidebar></t-control-sidebar>
    <t-footer></t-footer>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import TemplateSidebar from "@/components/Sidebar.vue";
import Body from "@/components/Body.vue";
import TemplateControlSidebar from "@/components/ControlSidebar.vue";
import Footer from "@/components/Footer.vue";

export default {
  components: {
    't-header': Header,
    't-sidebar': TemplateSidebar,
    't-body': Body,
    't-control-sidebar': TemplateControlSidebar,
    't-footer': Footer
  }
}

require('../assets/plugins/jquery/jquery.min.js')
// require('../assets/plugins/bootstrap/js/bootstrap.bundle.min.js')
require('../assets/dist/js/adminlte.min.js')

</script>

<style>

  @import '../assets/plugins/fontawesome-free/css/all.min.css';
  @import '../assets/dist/css/adminlte.min.css';
  @import url("https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700");
</style>
