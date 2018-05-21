<template>
  <div style="background-color: white">
    <breadcrumb :breadcrumb_data="breadcrumb_data"></breadcrumb>
    <el-form :inline="true" :model="search_form">
      <el-form-item>
        <el-button type="primary" @click="detail">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="list" stripe border style="width: 100%">
      <el-table-column prop="username" label="用户名称" width="180"></el-table-column>
      <el-table-column prop="nickname" label="昵称"></el-table-column>
      <el-table-column prop="enabled" label="是否启用" :formatter="formatEnabled"></el-table-column>
      <el-table-column fixed="right" label="操作" width="180">
        <template scope="scope">
          <el-button type="text" size="small" @click="resetpwd(scope.row)">重置密码</el-button>
          <el-button type="text" size="small" @click="detail(scope.row)">编辑</el-button>
          <el-button type="text" size="small" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="用户" v-model="dialog_form"
               :close-on-click-modal="false" :close-on-press-escape="false" :show-close="false">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户名" :rules="[{required: true, message: '请输入用户名称', trigger: 'blur'}]">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname"></el-input>
        </el-form-item>
        <el-form-item label="启用">
          <enabledSelect placeholder="请选择是否启用" ref="enabledSelect" :dict_options="enabled_options"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialog_form = false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </div>
    </el-dialog>
    <paginator @load_data="load_data" ref="paginator" :page="page" :limit="limit" :total="total"></paginator>
  </div>
</template>


<script>
  import Paginator from '../../components/Paginator.vue'
  import Breadcrumb from '../../components/Breadcrumb.vue'
  import SimpleSelect from '../../components/SimpleSelect.vue'
  import Dict from '../../components/Dict.vue'


  export default {
    components: {
      "paginator": Paginator,
      "breadcrumb": Breadcrumb,
      "enabledSelect": SimpleSelect
    },
    data() {
      return {
        breadcrumb_data: [
          {path: '/', name: '首页'},
          {path: '', name: '系统管理'},
          {path: '/user', name: '用户管理'}
        ],
        list: [],
        dialog_form: false,
        enabled_options: Dict.YESNO,
        form: {
          username: '',
          nickname: '',
          enabled: 0,
          id: ''
        }
      }
    },
    created () {
      this.load_data()
    },
    methods: {
      load_data: function (params) {
        var v = this;
        var url_ = '/user/list';
        if (v.$refs.paginator != undefined) {
          var pagination = '?page=' + v.$refs.paginator.page + '&limit=' + v.$refs.paginator.limit;
          url_ += pagination;
        }
        v.$api.get(url_, params, function (resp) {
          v.list = resp.data.items;
          v.total = resp.data.pagination.total;
        })
      },
      save: function () {
        var v = this;
        //todo-yll-fixme 验证不生效
        v.$refs['form'].validate(valid => {
          if (!valid) {
            console.log('validate error!');
            return;
          }
        });
        var params = {
          username: v.form.username,
          nickname: v.form.nickname,
          id: v.form.id,
          enabled: v.$refs.enabledSelect.value
        }
        var url_ = '/user';
        if (params.id != undefined && params.id != '') {
          v.$api.put(url_, params, function (resp) {
            v.dialog_form = false;
            v.load_data();
          })
        } else {
          v.$api.post(url_, params, function (resp) {
            v.dialog_form = false;
            v.load_data();
          })
        }
      },
      del: function (row) {
        var v = this;
        var url_ = '/user/' + row.id;
        v.$api.delete(url_, {}, function (resp) {
          v.load_data();
        })
      },
      resetpwd: function (row) {
        var v = this;
        var url_ = '/user/resetpwd/' + row.id;
        v.$api.put(url_, {}, function (resp) {
          v.load_data();
        })
      },
      detail: function (row) {
        var v = this;
        //todo-yll-fixme 设置用户名称不可编辑
        v.dialog_form = true;
        if (row.id == undefined) {
          v.form.username = '';
          v.form.nickname = '';
          v.form.id = '';
          if (v.$refs.enabledSelect) v.$refs.enabledSelect.setValue(Dict.YESNO_.N);
          return;
        }
        var url_ = '/user/' + row.id;
        v.$api.get(url_, {}, function (resp) {
          v.form.username = resp.data.username;
          v.form.nickname = resp.data.nickname;
          v.$refs.enabledSelect.setValue(resp.data.enabled);
          v.form.id = resp.data.id;
        })
      },
      formatEnabled(row, column) {
        return Dict.getDesc(Dict.YESNO, row.enabled);
      }
    }
  }
</script>
