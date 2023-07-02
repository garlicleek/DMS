import { createRouter, createWebHashHistory } from "vue-router";

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      component: () => import("../components/Login.vue"),
    },
    {
      path: "/login",
      component: () => import("../components/Login.vue"),
    },
    {
      path: "/main",
      component: () => import("../components/Main.vue"),
    },
  ],
});

// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  // to:将要访问的路径
  // from:从哪里访问的路径
  // next:之后要做的任务，是一个函数
  //    next（）放行， next（'/URL'）强制跳转的路径。
  if (to.path == "/login") return next(); // 访问路径为登录
  // 获取flag
  const token = window.sessionStorage.getItem("token"); // session取值
  if (token == undefined || token == "") return next("/login"); // 没登录去登录
  next();
});

export default router;
