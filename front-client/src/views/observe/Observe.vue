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
                :rowHeaders="gridProps.rowHeaders"
                :data="gridProps.data"
                :columns="gridProps.columns"
                :pageOptions="gridProps.pageOptions"
                @click="detailView"
              ></grid>

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

//TuiGrid.setLanguage('ko');
TuiGrid.applyTheme('striped');


export default {
  components: {
    grid: Grid

  },
  data() {
    return {
      rows: [],
      pageSize : 50,
      columns: [ // for columnData prop
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
    }
  },
  mounted() {
    // this.$refs.tuiGrid.invoke('use', 'Net', {
    //   perPage: 10,
    //   api: { readData: 'api/readData' }
    // });
    // this.$http.get(`${this.$store.state.HOST}/observation.diary/list`)
    //   .then(({data}) => {
    //     let contents = data.detail.contents.contents
    //     let observeData = []
    //     for (let x of contents) {
    //       observeData.push({'name': x.entityManagementNumber})
    //     }
    //
    //     const option = {
    //       pageState: {
    //         page: 1, totalCount: 100, perPage: 10
    //       }
    //       , filterState: {
    //         columnName: ['name']
    //       }
    //     }
    //     this.$refs.tuiGrid.invoke('resetData', observeData, option);

    // const addColumn =[
    //   {
    //     header: 'Artist2',
    //     name: 'artist2'
    //   }
    // ]
    // this.$refs.tuiGrid.invoke('setColumns',addColumn );
    // })
    console.log('getRootElement = ', this.$refs.tuiGrid.getRootElement());
    console.log('getColumns = ', this.$refs.tuiGrid.invoke('getColumns'));
    console.log('getPagination = ', this.$refs.tuiGrid.invoke('getPagination'));
    console.log('getPaginationTotalCount = ', this.$refs.tuiGrid.invoke('getPaginationTotalCount'));
    //console.log(this.$refs.tuiGrid.invoke('getData'))
    // this.$refs.tuiGrid.invoke('on','response', function (ev) {
    //   // 성공/실패와 관계 없이 응답을 받았을 경우
    //   console.log('ev',ev)
    // })

  },
  created() {

    this.gridProps = {
      scrollX: true
      , scrollY: true
      , rowHeaders: ['rowNum']
      , pageOptions: {
        "perPage": this.pageSize,
        //useClient: true
      }
      /*데이터를 datasource 백엔드 호출 readData로 직접 가져오고 싶은경우에 사용함.
      1. 응답 객체에 대한 포맷과 페이징 옵션을 맞춰 주어야 만 동작된다.
      2. 응답데이터를 커스텀하게 조작 할수 없음.*/
      , data: {
        api: {
          readData: {
            url: `${this.$store.state.HOST}/observation.diary/list`
            , method: 'get'
            , initParams: { page: 0, size: this.pageSize }
          }
        }
        ,hideLoadingBar: true
      }
      , columns: [ // for columnData prop
        {
          header: 'EntityIdentificationNumber',
          name: 'entityIdentificationNumber',
          sortingType: 'desc',
          sortable: true
        },
        {
          header: 'cureDate',
          name: 'cureDate'
        }
        ,
        {
          header: 'cureAgeOfMonth',
          name: 'cureAgeOfMonth'
        }
        ,
        {
          header: 'ageOfMonth',
          name: 'ageOfMonth'
        }
        ,
        {
          header: 'medicationName',
          name: 'medicationName'
        }
        ,
        {
          header: 'injectionMethod',
          name: 'injectionMethod'
        }
        ,
        {
          header: 'treatmentDetails',
          name: 'treatmentDetails'
        }
        ,
        {
          header: 'location',
          name: 'location'
        }
        ,
        {
          header: 'remark',
          name: 'remark'
        }
        ,
        {
          header:'withdrawalPeriodExpirationDate',
          name:'withdrawalPeriodExpirationDate'
        }]
    };


  }

  , methods: {
    observeList() {

    },
    detailView (ev) {
      //console.log('click event: ', ev)
      console.log(ev.instance.getValue(ev.rowKey, 'medicationName'))
    }

  }
};

</script>

<style scoped="scoped">
</style>
