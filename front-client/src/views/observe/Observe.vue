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
            <div class="card-footer">

            </div>
          </div>

          <div class="card">
            <div class="card-header">
              <div class="row align-items-center">
                <i class="fa fa-align-justify"></i>
                <div class="col-12 col-xl mb-3 mb-xl-0">Observe List</div>
                <div class=" mb-3 mb-xl-0">
                  <button class="btn btn-block btn-outline-success" type="button">register</button>
                </div>
              </div>
            </div>
            <div class="card-body">
              <grid
                ref="tuiGrid"
                :data="gridProps.data"
                :columns="gridProps.columns"
                :rowHeaders="gridProps.rowHeaders"
                :columnOptions="gridProps.columnOptions"
              ></grid>

             <!-- <grid ref="tuiGrid" :data="rows" :columns="columns"/>-->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import 'tui-grid/dist/tui-grid.css';
  import {Grid} from '@toast-ui/vue-grid';
  import 'tui-pagination/dist/tui-pagination.css';
  import TuiGrid from 'tui-grid';

  TuiGrid.setLanguage('ko');
  TuiGrid.applyTheme('striped');


  export default {
    components: {
      grid: Grid

    },
    data() {
      return {
        rows: [],
        columns: []
      }
    },
    mounted() {
      // this.$refs.tuiGrid.invoke('use', 'Net', {
      //   perPage: 10,
      //   api: { readData: 'api/readData' }
      // });
      console.log(this.$refs.tuiGrid.getRootElement())
      this.$http.get(`${this.$store.state.HOST}/observation.diary/list`)
        .then(({data}) => {
          console.log(data)
          let contents = data.detail.contents.contents
          var observeData = []
          for (let x of contents) {
            console.log(x.entityManagementNumber)
            observeData.push({'name': x.entityManagementNumber})
          }
          this.$refs.tuiGrid.invoke('resetData', observeData);
        })


    },
    created() {

      this.gridProps = {
        scrollX: true,
        scrollY: true,
        rowHeaders: ['rowNum'],
        pageOptions: {
          perPage: 5
          , useClient: true
        },
        data:[]
        // {
        //   api: {
        //     readData: {
        //       url: `${this.$store.state.HOST }/observation.diary/list`
        //       , method: 'get'
        //       , initParams: {
        //         page:0
        //         ,size:50
        //       }
        //     }
        //   }
        //   //,hideLoadingBar: true
        // }
        ,
        columns:[ // for columnData prop
          {
            header: 'Name',
            name: 'name',
            sortingType: 'desc',
            sortable: true
          },
          {
            header: 'Artist1',
            name: 'artist'
          }]
      };


      //TuiGrid.getRootElement();

      this.observeList()

    }

    , methods: {
      observeList() {
        this.$http.get(`${this.$store.state.HOST}/observation.diary/list`)
          .then(({data}) => {
            console.log(data)
            let contents = data.detail.contents.contents
            var observeData = []
            for (let x of contents) {
              console.log(x.entityManagementNumber)
              observeData.push({'name': x.entityManagementNumber})
            }

          })


        //
        // this.gridProps.data = {
        //   "result": true,
        //   "data": {
        //     "contents": [{"name":1234}]
        //   }
          // ,"pagination": {
          //   "page": 1,
          //   "totalCount": 100
          // }
        // }

        // this.gridProps.columns=[ // for columnData prop
        //   {
        //     header: 'Name',
        //     name: 'name',
        //     sortingType: 'desc',
        //     sortable: true
        //   },
        //   {
        //     header: 'Artist1',
        //     name: 'artist'
        //   }]



        this.columns=[ // for columnData prop
          {
            header: 'Name',
            name: 'name',
            sortingType: 'desc',
            sortable: true
          },
          {
            header: 'Artist1',
            name: 'artist'
          }]



      },

    }
  };

</script>

<style scoped="scoped">
</style>
