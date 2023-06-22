// 超时时间是5秒
axios.defaults.timeout = 5000;
// 允许跨域
axios.defaults.withCredentials = true;
// Content-Type 响应头
axios.defaults.headers.post["Content-Type"] =
  "application/x-www-form-urlencoded;charset=UTF-8";
// 基础url
axios.defaults.baseURL ='http://localhost:82';
// axios.defaults.baseURL = 线上生产环境地址;
