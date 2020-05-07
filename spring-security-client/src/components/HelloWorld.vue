<template>
  <div class='login-page'>
    <el-row>
      <el-col :span='12' :offset='6'>
        <el-form ref='loginForm'
                 :model='loginForm' :rules='loginRules' class='login-form' auto-complete='on'
                 label-position='left'
                 label-width='90px'>
          <div class='title-container'>
            <h2 class='title'>Login</h2>
          </div>
          <el-form-item prop='loginId'>
            <el-input v-model='loginForm.loginId'
                      placeholder='Username'
                      name='username'
                      type='text'
                      auto-complete='on'
                      prefix-icon='el-icon-user'
                      clearable
                      class='form-cell-input'>
            </el-input>
          </el-form-item>
          <el-form-item prop='password'>
            <el-input :type='passwordType'
                      v-model='loginForm.password'
                      placeholder='Password'
                      name='password'
                      auto-complete='on'
                      prefix-icon='el-icon-lock'
                      clearable
                      class='form-cell-input'/>
          </el-form-item>
          <div class='form-ops'>
            <div class='login-btn'>
              <el-button
                :loading='loading'
                type='primary'
                class='login-el-btn'
                @click.native.prevent='handleLogin'>Login
              </el-button>
            </div>
            <span @click='handleFormReset'>Reset</span>
          </div>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import {post, get} from '../components/http.util'

  import _ from 'lodash'

  export default {
    name: 'Login',
    data () {
      return {
        loginForm: {
          loginId: '',
          password: '',
          code: '',
        },
        countdown: 0,
        passwordType: 'password',
        loading: false,
        loginRules: {
          loginId: [{required: true, trigger: 'blur', message: 'Username is required'}],
          password: [{required: true, trigger: 'blur', message: 'Password is required'}],
          code: [{required: true, trigger: 'blur', message: 'Verification code is required'}],
        },
        errorMsg: '',
      }
    },
    computed: {
      geoUserInfo () {
        const geoUserInfoStr = get('geoUserInfo')
        if (!_.isEmpty(geoUserInfoStr)) {
          return JSON.parse(geoUserInfoStr)
        }
        return null
      },
    },
    async created () {
    },
    methods: {
      async handleLogin () {
        this.errorMsg = ''
        let loginType = 'FAILED'
        this.$refs.loginForm.validate(async valid => {
          if (valid) {
            this.loading = true
            try {
              const {loginId, password, code} = this.loginForm
              const loginResult = await this.login({username: loginId, password})
              console.log(loginResult);
              if(loginResult.status === 200) {
                localStorage.setItem('token', loginResult.data.access_token);
                console.log(localStorage.getItem('token'));
                this.$router.push({name: 'Home'});
              }
            } catch (e) {
              loginType = 'FAILED'
            }
            this.loading = false
          }
        })
      },
      async login ({username, password}) {
        try {
          let result = await post(`/oauth/token`, {
            username: username,
            password: password,
            client_id: 'client_1',
            client_secret: '123456',
            grant_type: 'password'
          })
          return result;
        } catch (e) {

        }
      },
      handleFormReset () {
        this.$refs['loginForm'].resetFields()
        this.errorMsg = ''
      },
      sendVerificationCode () {
        try {
          post(`/api/generateGensetAuthenticationCode`, this.loginForm)
          this.countdown = 60
          this.countDownKickStart()
        } catch (e) {
          this.$notify.error(e.response.data.message || 'Server error, please try later!')
          // eslint-disable-next-line
          console.log(e)
        }
      },
      countDownKickStart () {
        if (this.countdown === 0) {
          return
        }
        this.countdown--
        setTimeout(this.countDownKickStart, 1000)
      },
    },
  }
</script>

<style lang='scss' scoped>

  .login-page {
    background: #fff;
    position: fixed;
    height: 100%;
    width: 100%;
  }
  .login-form {
    border: 1px solid #ccd0d6 !important;
    border-radius: 1em !important;
    padding: 30px 20px 20px 20px;
    width: 540px;
    height: 360px;
    position: relative;

    .form-cell-input {
      width: 365px;
    }

    .err-msg {
      color: #F56C6C;
      font-size: 12px;
      line-height: 1;
    }

    .form-ops {
      position: relative;
      .login-btn {
        margin-top: 0;
        text-align: center;
        .login-el-btn {
          width: 200px;
          font-size: 16px;
          background-color: #1b4f8d;
        }
      }
      span {
        position: absolute;
        right: 82px;
        top: 10px;
        color: #7B7B7B;
        cursor: pointer;
      }
    }
  }

  .title-container {
    text-align: center;
    font-size: 18px;
    margin-bottom: 7%;
  }

  .el-row {
    margin-top: 8% !important;
  }
  .active-button {
    color: #fff !important;
    background-color: #1b4f8d !important;
  }
  .inactive-button {
    color: #fff !important;
    background-color: rgb(189, 197, 202) !important;
  }

  .el-col-offset-6 {
    margin-left: 34% !important;
  }

</style>
