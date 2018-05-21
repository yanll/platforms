<template>
  <div style="text-align: right;">
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      @logs="logs"
      :page-sizes="[10, 20, 50]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
  </div>
</template>
<script>
  export default {
    methods: {
      handleSizeChange(val) {
        this.limit = val;
        this.$emit('load_data');
        console.log(`limit:${val}`);
      },
      handleCurrentChange(val) {
        this.page = val;
        this.$emit('load_data');
        console.log(`page:${val}`);
      },
      logs(val) {
        console.log(val);
      },
    },
    props: {
      page: {type: Number, default: 1},
      limit: {type: Number, default: 10},
      total: {type: Number, default: 0}
    },
    data() {
      return {};
    }
  }
</script>
