<template>
  <el-breadcrumb :breadcrumb_data="breadcrumb_data" separator="/" style="margin-bottom: 6px;">
    <el-breadcrumb-item v-for="i in breadcrumb_data" :key="i.path" :to="{path:i.path}">{{i.name}}</el-breadcrumb-item>
  </el-breadcrumb>
</template>
<script>
  export default {
    data() {
      return {
        breadcrumb_data: []
      };
    },
    created () {
      this.load_data();
    },
    methods: {
      load_data: function (params) {
        var v = this;
        if (v.breadcrumb_data != undefined) {
          console.log(v.breadcrumb_data);
        }
      }
    },
    props: {
      breadcrumb_data: {type: Object, defaut: []}
    }
  }
</script>
