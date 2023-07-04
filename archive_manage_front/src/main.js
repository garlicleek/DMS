import { createApp } from "vue";
import axios from "axios";
import "./style.css";
import ElementPlus from "element-plus";
import { ElMessage } from "element-plus";
import zhCn from "element-plus/dist/locale/zh-cn.mjs";
import "dayjs/locale/zh-cn";
import "element-plus/dist/index.css";
import "element-plus/theme-chalk/src/message.scss";
import App from "./App.vue";
import router from "./router";

const app = createApp(App);

app.use(ElementPlus, {
  locale: zhCn,
});
app.use(router);
app.mount("#app");

axios.defaults.baseURL = "http://123.249.31.57:82";
axios.defaults.timeout = 5000;
// axios.defaults.headers.common["Content-Type"] =
//   "application/x-www-form-urlencoded";
axios.interceptors.request.use(
  (config) => {
    //例：若存在token则带token
    const token = window.sessionStorage.getItem("token");
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (err) => {
    console.log("请求失败拦截=>", err);
    return err;
  }
);

axios.interceptors.response.use(
  (res) => {
    //例：后端数据处理错误，并返回错误原因；前端获取错误原因并展示
    if (res.data.state == 0) {
      ElMessage({
        message: res.data.message + "，请重试！",
        type: "error",
      });
    }
    return res ? res.data : res;
  },
  (err) => {
    //打印完整的错误信息
    console.log("响应失败拦截=>", err);
    //也可以在这里对不同的错误码做不同的展示处理
    ElMessage({
      message: "无权访问",
      type: "error",
    });
    throw err;
  }
);

app.config.globalProperties.$http = axios;
