<template>
  <div style="height: 100%">
    <el-container>
      <el-main>
        <el-form  :model="loginForm" :rules="rules"  ref="loginForm" label-width="100px" class="login-box">
          <el-form-item label="用户名" prop="phone">
            <el-input v-model="loginForm.phone"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="loginForm.password"></el-input>
          </el-form-item>
          <el-form-item label="验证码" prop="verify">
            <el-image :src="`${this.$store.state.url}/code/image?deviceId=${this.loginForm.deviceId}`"
            v-if="this.changeVerify" @click="refreshVerifyImg"></el-image>
          </el-form-item>
          <el-input type="code" v-model="loginForm.code"></el-input>
          <el-form-item label-width="20px">
            <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
      <!--      <el-footer>Footer</el-footer>-->
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Login",
  created() {
  },
  data() {
    return {
      changeVerify: true,
      loginForm: {
        phone: '',
        password: '',
        deviceId: '123',
        code: '',
      },
      rules: {
        phone: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          this.$axios.post(this.$store.state.url+'/auth/login', this.loginForm).then(res => {
            //处理登录逻辑
            if (res.data.success) {
              // 保存token到全局变量
              const token = res.data.data.token
              const userInfo = res.data.data
              const menus = res.data.data.menus
              _this.$store.commit("SET_TOKEN",token)
              _this.$store.commit("SET_USERINFO",userInfo)
              _this.$store.commit('SET_MENUS',menus)
              // 跳转页面
              _this.$router.push("/index")
            } else {
              alert(res.data.msg)
            }
          })
        } else {
          this.refreshVerifyImg();
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    createUUId() {
      let temp_url = URL.createObjectURL(new Blob());
      let uuid = temp_url.toString();
      URL.revokeObjectURL(temp_url);
      return uuid.substr(uuid.lastIndexOf("/") + 1);
    },
    refreshVerifyImg() {
      this.loginForm.code = "";
      this.changeVerify = false;
      this.loginForm.deviceId = this.createUUId();
      setTimeout(() => {
        this.changeVerify = true;
      }, 0);
    }
  },
  mounted() {
    this.loginForm.deviceId = this.createUUId();
  }
}
</script>

<style scoped>

</style>
