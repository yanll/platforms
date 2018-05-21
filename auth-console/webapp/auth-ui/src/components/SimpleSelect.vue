<!--todo-yll-fixme value 默认值设置-->
<template>
  <el-select v-model="value" :dict_options="dict_options" :placeholder="placeholder" @change="change"
             @setValue="setValue">
    <el-option v-for="item in dict_options" :key="item.value" :label="item.desc" :value="item.value"></el-option>
  </el-select>
</template>

<script>
  export default {
    data() {
      return {
        dict_options: [],
        placeholder: '',
        value: ''
      }
    },
    props: {
      dict_options: {type: Object, defaut: {}},
      placeholder: {type: String, defaut: ''}
    },
    created () {
      console.log("SimpleSelect.vue created.");
    },
    mounted(){
      console.log("SimpleSelect.vue mounted.");
    },
    methods: {
      change(v){
        if (v == undefined) return;
        console.log('change select value:' + v)
      },
      setValue(v){
        console.log('set select value:' + v)
        if (v == undefined) return;
        this.value = v;
      }
    }
  }
</script>
