// 配置API接口地址
var API_SERVER = 'http://localhost:8080';
// 引用superagent
var request = require('superagent');

// 返回在vue模板中的调用接口
export default {
  get: function (url, params, success, failure) {
    return _api_base('GET', 'text/plain', url, params, success, failure)
  },
  post: function (url, params, success, failure) {
    return _api_base('POST', 'application/json', url, params, success, failure)
  },
  put: function (url, params, success, failure) {
    return _api_base('PUT', 'application/json', url, params, success, failure)
  },
  delete: function (url, params, success, failure) {
    return _api_base('DELETE', 'text/plain', url, params, success, failure)
  },
  upload: function (url, type, params, success, failure) {
    return _api_base('POST', 'multipart/form-data', url, params, success, failure)
  },
}


// 自定义判断元素类型JS
function toType(obj) {
  return ({}).toString.call(obj).match(/\s([a-zA-Z]+)/)[1].toLowerCase()
}
// 参数过滤函数
function filter_null(o) {
  for (var key in o) {
    if (o[key] == null) {
      delete o[key]
    }
    if (toType(o[key]) == 'string') {
      o[key] = o[key].trim()
      if (o[key].length == 0) {
        delete o[key]
      }
    }
  }
  return o
}
/*
 接口处理函数
 */
function _api_base(method, type, url, params, success, failure) {
  var r = request(method, API_SERVER + url).type(type)
  if (params != undefined && params != null) {
    params = filter_null(params);
    if (method === 'POST' || method === 'PUT') {
      if (toType(params) == 'object') {
        params = JSON.stringify(params);
      }
      r = r.send(params)
    } else if (method == 'GET' || method === 'DELETE') {
      r = r.query(params)
    }
  }
  r.end(function (err, res) {
    if (err) {
      if (res == undefined) {
        console.log('服务器无响应，请稍后再试！');
        return;
      }
      console.log('API Error, HTTP CODE: ' + res.status);
      return;
    }
    if (res.body.code == 200) {
      if (success) {
        success(res.body);
      }
    } else {
      if (failure) {
        failure(res.body);
      } else {
        console.log('error: ' + JSON.stringify(res.body));
      }
    }
  });
};
