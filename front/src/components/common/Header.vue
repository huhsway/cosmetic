<template>
  <nav class="navbar navbar-expand-sm navbar-light bg-light" role="navigation">
    <!-- container -->
    <vue-nested-menu :source="menu" />
    <div class="container">
      <a class="navbar-brand mr-auto" href="/">cosmetic</a>
      <button
        aria-controls="navbarTop"
        aria-expanded="false"
        aria-label="Toggle navigation"
        class="navbar-toggler"
        data-target="#navbarTop"
        data-toggle="collapse"
        type="button"
        @click="toggleNavbar"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div
        id="navbarTop"
        :class="{ show: isNavOpen }"
        class="collapse navbar-collapse"
      >
        <ul class="navbar-nav mr-auto"></ul>
        <ul class="nav navbar-nav">
          <router-link
            v-if="!isAuthenticated"
            active-class="active"
            class="nav-item"
            tag="li"
            to="/login"
          >
            <a class="nav-link">로그인</a>
          </router-link>
          <li v-if="isAuthenticated" class="li-pointer nav-item">
            <a class="nav-link" @click="LOGOUT"> 로그아웃 </a>
          </li>
          <router-link
            v-if="!isAuthenticated"
            active-class="active"
            class="nav-item"
            tag="li"
            to="/register"
          >
            <a class="nav-link">회원가입</a>
          </router-link>
        </ul>
      </div>
    </div>
    <!-- /.container -->
  </nav>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import VueNestedMenu from "vue-nested-menu";
import Vue from "vue";
import ApiService from "@/apis/api";

Vue.use(VueNestedMenu);

export default {
  name: "Header",
  data() {
    return {
      isNavOpen: false,
      menu: {},
    };
  },
  computed: {
    ...mapGetters(["cartItemList"]),
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    },
    numItems() {
      return this.cartItemList.length;
    },
  },
  methods: {
    // ...mapActions(["logout"]),
    ...mapMutations(["LOGOUT"]),
    toggleNavbar() {
      this.isNavOpen = !this.isNavOpen;
    },
  },
  mounted() {
    ApiService.get(`/api/v1/categories`).then(
      function (response) {
        let data = JSON.parse(
          JSON.stringify(response.data)
            .replaceAll("null", '""')
            .replace("root", "카테고리")
            .replaceAll("/5", "/categoryRedirect/5")
        );
        this.menu = data;
      }.bind(this)
    );
  },
};
</script>

<style lange="sass" scoped>
.navbar-btn a {
  color: white;
}

.li-pointer {
  cursor: pointer;
}

.li-pointer:hover {
  cursor: pointer;
}
</style>
