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
                      <th>entityNumber</th>
                      <th>indentityNumber</th>
                      <th>date</th>
                      <th>gender</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="item in cattleData">
                      <td>{{ item.entityManagementNumber}}</td>
                      <td>{{ item.entityIdentificationNumber}}</td>
                      <td>{{ item.birthDay }}</td>
                      <td>{{ item.gender }}</td>
                      <td>Member</td>
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

    data(){
      return {
        cattleData:[]
      }
    },
    mounted(){
      this.$http.get(this.$store.state.host+'/cattle/cattle_management_list')
      .then((res)=>{
        console.log(res.data)
        this.$data.cattleData = res.data.contents
      })
      .catch((error)=>{

      })

    },

    methods:{
      cattleSave : function () {
          this.$route.push('/cattle/cattleSave')
      },
      pageMove : function (currentPage) {
        this.$http.get(this.$store.state.host+'/cattle/cattle_management_list?page' + currentPage)
          .then((res)=>{
            console.log(res.data)
            this.$data.cattleData = res.data.contents
          })
          .catch((error)=>{

          })

      }
    }
  }

</script>

<style scoped="scoped">

</style>
