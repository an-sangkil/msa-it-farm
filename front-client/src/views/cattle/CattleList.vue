<template>
  <div class="container-fluid">
    <div class="fade-in">
      <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <!-- <div class="card-header"><i class="fa fa-align-justify"></i>
                     header
            </div> -->
            <div class="card-body">
            </div>
          </div>
          <div class="card">
                    <div class="card-header">
                      <div class="row align-items-center">
                        <i class="fa fa-align-justify"></i>
                        <div class="col-12 col-xl mb-3 mb-xl-0">Cattle List</div>
                        <div class=" mb-3 mb-xl-0">
                          <button class="btn btn-block btn-outline-success" type="button">등록</button>
                        </div>
                      </div>
                    </div>
                    <div class="card-body">
                      <table class="table table-responsive-sm">
                        <thead>
                          <tr>
                            <th>Username</th>
                            <th>Date registered</th>
                            <th>Role</th>
                            <th>Status</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>Samppa Nori</td>
                            <td>2012/01/01</td>
                            <td>Member</td>
                            <td><span class="badge badge-success">Active</span></td>
                          </tr>
                          <tr>
                            <td>Estavan Lykos</td>
                            <td>2012/02/01</td>
                            <td>Staff</td>
                            <td><span class="badge badge-danger">Banned</span></td>
                          </tr>
                          <tr>
                            <td>Chetan Mohamed</td>
                            <td>2012/02/01</td>
                            <td>Admin</td>
                            <td><span class="badge badge-secondary">Inactive</span></td>
                          </tr>
                          <tr>
                            <td>Derick Maximinus</td>
                            <td>2012/03/01</td>
                            <td>Member</td>
                            <td><span class="badge badge-warning">Pending</span></td>
                          </tr>
                          <tr>
                            <td>Friderik Dávid</td>
                            <td>2012/01/21</td>
                            <td>Staff</td>
                            <td><span class="badge badge-success">Active</span></td>
                          </tr>
                        </tbody>
                      </table>
                      <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="#">Prev</a></li>
                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">4</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                      </ul>
                    </div>
                  </div>

          <button v-on:click="cattleSave">add</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

  export default {
    created(){

        this.$store.commit('changeHome',{name:'Home'})
        this.$store.commit('changeTitle',{name:'Cattle'})
        this.$store.commit('changeSubtitle',{name:'list'})

    },

    mounted(){
      this.$http.get(this.$store.state.host+'/cattle/cattle_management_list')
      .then((res)=>{
        console.log(res.data)
      })
      .catch((error)=>{

      })

    },

    methods:{
      cattleSave : function () {
        location.href = "/cattle/cattleSave"
      }
    }
  }

</script>

<style scoped="scoped">

</style>
