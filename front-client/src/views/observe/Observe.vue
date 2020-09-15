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
              <grid :data="gridProps.data" :columns="gridProps.columns" :rowHeaders="gridProps.rowHeaders" :pageOptions="gridProps.pageOptions" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import 'tui-grid/dist/tui-grid.css';
  import { Grid } from '@toast-ui/vue-grid';
  import 'tui-pagination/dist/tui-pagination.css';
  import TuiGrid from 'tui-grid';

  TuiGrid.setLanguage('ko');
  TuiGrid.applyTheme('striped');

  export default {
    components: {
      grid: Grid
    },
    created() {
      this.gridProps = {
        scrollX: false,
        scrollY: false,
        minBodyHeight: 30,
        rowHeaders: ['rowNum'],
        pageOptions: {
          useClient:true,
          perPage: 5
        },
        data: [
          // for rowData prop
          {
            name: 'Beautiful Lies',
            artist: 'Birdy'
          },
          {
            name: 'X',
            artist: 'Ed Sheeran'
          },
          {
            name: 'Beautiful Lies',
            artist: 'Birdy'
          },
          {
            name: 'X',
            artist: 'Ed Sheeran'
          },
          {
            name: 'Beautiful Lies',
            artist: 'Birdy'
          },
          {
            name: 'X',
            artist: 'Ed Sheeran'
          }

        ],
        columns: [
          // for columnData prop
          {
            header: 'Name',
            name: 'name',
            sortingType:'desc',
            sortable: true
          },
          {
            header: 'Artist',
            name: 'artist'
          }
        ]
      };
    }
  };


</script>

<style scoped="scoped">
</style>
