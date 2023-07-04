<template>
  <el-form
    style="width: 30%;height:40%;position: fixed;left: 35%;top:25%;box-shadow: var(--el-box-shadow);background-color: rgba(255, 255, 255, 0.6);">
    <span style="font-size:150%;position: absolute;top: 10%;left: 35%;">档案管理系统</span>
    <el-form-item label="用户名" style="position: absolute;left: 20%;top:35%;width: 60%;">
      <el-input placeholder="请输入用户名" type="text" v-model="user.username" size="large" />
    </el-form-item>
    <el-form-item label="密&nbsp;&nbsp;&nbsp;码" style="position: absolute;left: 20%;top: 50%;width: 60%;">
      <el-input placeholder="请输入密码" type="password" v-model="user.password" size="large" />
    </el-form-item>
    <el-form-item style="position: absolute;left: 20%;top: 80%;width: 60%;">
      <el-button type="primary" @click="onSubmit" size="large" style="width: 350px">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { ElMessage } from 'element-plus';
export default {
  namespaced: true,
  data() {
    return {
      user: {
        username: '',
        password: ''
      }
    }
  },
  beforeCreate() {
    document.querySelector('body').setAttribute('style', "background: url('http://123.249.31.57:82/file/background.png');background-size:100%")
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
  methods: {
    onSubmit() {
      this.$http.get('/user/login', {
        params: {
          username: this.user.username,
          password: this.user.password
        }
      }).then(res => {
        if (res.state == 1) {
          window.sessionStorage.setItem('token', res.data);
          this.$router.push('/main');
        } else {
          ElMessage({
            message: '用户名或密码不正确',
            type: 'error',
          })
        }
      });
    }
  }
}
</script>