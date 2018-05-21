<script>
  export default {
    SYSTEM_PORTAL: [
      {value: 1, desc: "授权控制台"},
      {value: 2, desc: "监控控制台"}
    ],
    SYSTEM_PORTAL_: {
      AUTH_CONSOLE: 1,
      MONITOR_CONSOLE: 2
    },
    YESNO: [
      {value: 1, desc: "是"},
      {value: 0, desc: "否"}
    ],
    YESNO_: {
      Y: 1,
      N: 0
    },
    getDesc: function (dict_arr, value) {
      for (var i = 0; i < dict_arr.length; i++) {
        if (dict_arr[i].value == value) return dict_arr[i].desc;
      }
    },
    getValue: function (dict_arr, desc) {
      for (var i = 0; i < dict_arr.length; i++) {
        if (dict_arr[i].desc == value) return dict_arr[i].value;
      }
    }
  }
</script>
