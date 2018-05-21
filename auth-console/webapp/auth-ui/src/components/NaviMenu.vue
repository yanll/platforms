<template>
  <el-menu>
    <el-menu-item @click="router_to('/')" index="2"><i class="el-icon-menu"></i>首页</el-menu-item>
    <el-submenu index="10">
      <template slot="title" index="2"><i class="el-icon-menu"></i>系统管理</template>
      <el-menu-item index="3" @click="router_to('/permission_group')">权限分组</el-menu-item>
      <el-menu-item index="4" @click="router_to('/menu')">菜单管理</el-menu-item>
      <el-menu-item index="5" @click="router_to('/user')">用户管理</el-menu-item>
      <el-submenu index="9">
        <template slot="title" index="6">日志管理</template>
        <el-menu-item index="7">系统日志</el-menu-item>
        <el-menu-item index="8">操作日志</el-menu-item>
      </el-submenu>
    </el-submenu>
  </el-menu>
</template>


<script>

  export default{
    methods: {
      router_to: function (path) {
        var v = this;
        v.$router.push({path: path});
      }
    }
  }

</script>

