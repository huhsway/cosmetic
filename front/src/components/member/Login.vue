<template>
  <div class="row">
    <div class="col-md-6 offset-md-3 col-sm-12 offset-sm-1">
      <form id="login-form" role="form" style="display: block;" @submit.prevent="onSubmit">
        <h3 class="text-center">로그인</h3>
        <div class="form-group">
          <input
              type="email"
              name="email"
              id="email"
              class="form-control"
              placeholder="Email Address"
              v-model="email"
              pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
              title="@와 .이 필요합니다."
              required
          />
        </div>
        <div class="form-group">
          <input
              type="password"
              name="password"
              id="password"
              class="form-control"
              placeholder="Password"
              v-model="password"
              pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
              title="최소 8자리의 대소문자 1개씩과 숫자로 비밀번호를 만들어 주세요."
              required
          />
        </div>

        <div class="form-group">
          <button type="submit" class="btn btn-success" style="width: 100%" :disabled="isLoading">
            <i v-if="isLoading" class="fa fa-spinner fa-spin" />
            로그인
          </button>
        </div>
        <div class="form-group">
          <div class="row">
            <div class="col-lg-12">
              <div class="text-center">
                <router-link to="/register">
                  <a>회원가입</a>
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>

import { mapActions } from 'vuex';

export default {
  data() {
    return {
      email: '',
      password: '',
      isLoading: false
    }
  },
  methods: {
    ...mapActions(['addMessage', 'clearMessage','requestLogin']),
    onSubmit() {
      this.isLoading = true
      let data = {
        email: this.email,
        password: this.password
      }
      this.requestLogin(data).then(() => {
        this.clearMessage();
        this.$router.push({
          name: 'home'
        })
      }).catch((error) => {
        this.email = ""
        this.password = ""
        if (error.response.status === 500){
          error = "아이디나 비밀번호를 다시 확인 해 주세요."
          let message_obj = {
            message: error,
            messageClass: "danger",
            autoClose: true
          }
          this.addMessage(message_obj);
        }
      }).then(() => {
        this.isLoading = false
      })
    }
  }
}
</script>
