<template>
  <div class="c-sidebar c-sidebar-dark c-sidebar-fixed c-sidebar-lg-show" id="sidebar">
    <div class="c-sidebar-brand d-lg-down-none">
      <svg class="c-sidebar-brand-full" width="118" height="46" alt="CoreUI Logo">
        <use xlink:href="../assets/brand/coreui.svg#full"></use>
      </svg>
      <svg class="c-sidebar-brand-minimized" width="46" height="46" alt="CoreUI Logo">
        <use xlink:href="../assets/brand/coreui.svg#signet"></use>
      </svg>
    </div>
    <ul class="c-sidebar-nav">
      <li class="c-sidebar-nav-item">
        <router-link v-bind:to="'/dashboard'">
          <a class="c-sidebar-nav-link" href="#">
            <svg class="c-sidebar-nav-icon">
              <use xlink:href="../vendors/@coreui/icons/svg/free.svg#cil-speedometer"></use>
            </svg>
            Dashboard<span class="badge badge-info">NEW</span>
          </a>
        </router-link>
      </li>

      <li class="c-sidebar-nav-title">Theme</li>
      <li class="c-sidebar-nav-item">
        <router-link v-bind:to="'/cattle/cattleList'">
          <a class="c-sidebar-nav-link" href="#">
            <svg class="c-sidebar-nav-icon">
              <use xlink:href="../vendors/@coreui/icons/svg/free.svg#cil-drop1"></use>
            </svg>
            Cattle Management
          </a>
        </router-link>
      </li>
      <li class="c-sidebar-nav-item">
        <router-link v-bind:to="'/diary/diaryList'">
        <a class="c-sidebar-nav-link" href="#">
        <svg class="c-sidebar-nav-icon">
          <use xlink:href="../vendors/@coreui/icons/svg/free.svg#cil-pencil"></use>
        </svg>
        Diary</a>
        </router-link>
      </li>

      <li class="c-sidebar-nav-item">
        <router-link v-bind:to="'/observe/observeList'">
          <a class="c-sidebar-nav-link" href="#">
            <svg class="c-sidebar-nav-icon">
              <use xlink:href="../vendors/@coreui/icons/svg/free.svg#cil-pencil"></use>
            </svg>
            Observe</a>
        </router-link>
      </li>


      <li class="c-sidebar-nav-title">Components</li>
      <li class="c-sidebar-nav-item c-sidebar-nav-dropdown">
        <a class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle" href="#">
          <svg class="c-sidebar-nav-icon">
            <use xlink:href="../vendors/@coreui/icons/svg/free.svg#cil-puzzle"></use>
          </svg>
          Base</a>
        <ul class="c-sidebar-nav-dropdown-items">
          <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="base/breadcrumb.html"><span class="c-sidebar-nav-icon"></span> Breadcrumb</a></li>
          <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link" href="base/tooltips.html"><span class="c-sidebar-nav-icon"></span> Tooltips</a></li>
        </ul>
      </li>


      <!--<li class="c-sidebar-nav-item mt-auto"><a class="c-sidebar-nav-link c-sidebar-nav-link-success" href="https://coreui.io" target="_top">
        <svg class="c-sidebar-nav-icon">
          <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-cloud-download"></use>
        </svg>
        Download CoreUI</a></li>
      <li class="c-sidebar-nav-item"><a class="c-sidebar-nav-link c-sidebar-nav-link-danger" href="https://coreui.io/pro/" target="_top">
        <svg class="c-sidebar-nav-icon">
          <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-layers"></use>
        </svg>
        Try CoreUI<strong>PRO</strong></a></li>-->
    </ul>
    <button class="c-sidebar-minimizer c-class-toggler" type="button" data-target="_parent" data-class="c-sidebar-minimized"></button>
  </div>
</template>

<script>

  export default {}
</script>
