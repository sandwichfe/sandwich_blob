<template>
 <div>  
   <div class="login-container" 
   v-loading.fullscreen.lock="isloading"
   element-loading-text="loading...">
    <div class="login-card" >
      <div class="login-title">管理员登录</div>
      <el-form status-icon :model="loginForm" :rules="rules" ref="ruleForm" class="login-form">
        <el-form-item prop="username">
          <el-input 
            v-model="loginForm.username"
            prefix-icon="el-icon-user-solid"
            placeholder="用户名"
            @keyup.enter.native="login" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            prefix-icon="iconfont el-icon-mymima"
            show-password
            placeholder="密码"
            @keyup.enter.native="login" />
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="login">登录</el-button>
    </div>
  </div>
 </div>
</template>

<script>
import { generaMenu } from '@/assets/js/menu'
export default {
  data: function () {
    return {
      loginForm: {
        username: '',
        password: '',
      },
      rules: {
        username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
        password: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
      },
       isloading: false,
    }
  },
  methods: {
    login() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          const that = this
          let param = new URLSearchParams()
          param.append('username', that.loginForm.username)
          param.append('password', that.loginForm.password)
          this.isloading = true;
          that.axios.post('/api/users/login', param).then(({ data }) => {
            if (data.flag) {
              that.$store.commit('login', data.data)
              generaMenu()
              that.$message.success('登录成功')
              this.isloading = false;
              that.$router.push({ path: '/' })
            } else {
              this.isloading = false;
              that.$message.error(data.message)
            }
          })
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
.login-container {
  height: 780px;
  margin: auto;
  overflow: hidden;
  background: radial-gradient(
      circle at 60% 90%,
      rgb(161 207 181),
      transparent 60%
    ),
    radial-gradient(
      circle at 20px 20px,
      rgb(253 253 253 / 80%),
      transparent 25%
    ),
    #e7eefcbf
}
.login-card {
  padding: 60px 45px 5px;
  width: 421px;
  height: 300px;
  margin: auto;
  margin-top:75px ;

  color: rgba(255, 255, 255, 0.8);
  text-align: center;
  border-bottom: 1px solid rgba(255, 255, 255, 0.4);
  border-left: 1px solid rgba(255, 255, 255, 0.4);
  background: linear-gradient( to top right, #fff, #f5f7fa );
  box-shadow: 10px -10px 20px rgba(0, 0, 0, 0.2),
  -10px 10px 20px rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(6px);  /* 元素后面区域添加模糊效果 */
  border-radius: 20px;
}
.login-title {
  color: #99bfbd;
  font-weight: bold;
  font-size: 1rem;
}
.login-form {
  margin-top: 1.2rem;
}
.login-card button {
  margin-top: 1rem;
  width: 100%;
}
</style>
