<template>
  <div style="background-color: white">
    <breadcrumb :breadcrumb_data="breadcrumb_data"></breadcrumb>
    <el-table :data="list" stripe border style="width: 100%">
      <el-table-column prop="permissionName" label="权限名称" width="180"></el-table-column>
      <el-table-column prop="url" label="路由地址"></el-table-column>
    </el-table>
    <paginator @load_data="load_data" ref="paginator" :page="page" :limit="limit" :total="total"></paginator>
  </div>
</template>


<script>

  import Paginator from '../../components/Paginator.vue'
  import Breadcrumb from '../../components/Breadcrumb.vue'

  export default {
    components: {
      "paginator": Paginator,
      "breadcrumb": Breadcrumb
    },
    data() {
      return {
        breadcrumb_data: [
          {path: '/', name: '首页'},
          {path: '', name: '系统管理'},
          {path: '/permission_group', name: '权限分组'},
          {path: '/permission_group/permissions', name: '权限设置'}
        ],
        list: []
      }
    },
    created () {
      this.load_data()
    },
    methods: {
      load_data: function (params) {
        var v = this;
        if (params == undefined) params = {}
        var url = '/permission_group/' + v.$route.params.id + '/permissions';
        v.$api.get(url, params, function (resp) {
          console.log(resp);
          v.list = resp.data.items;
        })
      }
    },
  }
</script>
