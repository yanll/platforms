<template>
  <el-row>
    <el-col span="24">
      <breadcrumb :breadcrumb_data="breadcrumb_data"></breadcrumb>
      <el-form :inline="true" :model="search_form">
        <el-form-item>
          <portalSelect placeholder="请选择系统" ref="portalSelect" :dict_options="portal_options"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
      <el-tree default-expand-all="true" :data="tree_data" :props="defaultProps" node-key="id" highlight-current="true"
               :render-content="renderContent">
      </el-tree>
    </el-col>
  </el-row>

</template>


<script>
  import Breadcrumb from '../../components/Breadcrumb.vue'
  import SimpleSelect from '../../components/SimpleSelect.vue'
  import Dict from '../../components/Dict.vue'


  export default {
    components: {
      "breadcrumb": Breadcrumb,
      "portalSelect": SimpleSelect
    },
    data() {
      return {
        breadcrumb_data: [
          {path: '/', name: '首页'},
          {path: '', name: '系统管理'},
          {path: '/menu', name: '菜单管理'}
        ],
        tree_data: [],
        defaultProps: {
          children: 'children',
          label: 'menu_name'
        },
        dialog_form: false,
        portal_options: Dict.SYSTEM_PORTAL,
        search_form: {}
      }
    },
    created () {

    },
    computed: {},
    methods: {
      renderContent(h, {node, data, store}) {
        return (
          <span>{node.label}:{data.url}</span>
        );
      },
      load_data: function (portal_id) {
        var v = this;
        var url_ = '/menu/tree/' + portal_id;
        v.$api.get(url_, {}, function (resp) {
          console.log(resp);
          v.tree_data = resp.data;
        })
      },
      onSearch: function () {
        var v = this;
        var portal_id = v.$refs.portalSelect.value;
        if (portal_id == undefined || portal_id == '') {
          console.log("请选择系统！");
          return;
        }
        this.load_data(portal_id);
      }
    },
  }
</script>
