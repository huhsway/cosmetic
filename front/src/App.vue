<template>
  <div class="d-flex flex-column sticky-footer-wrapper">
    <main class="flex-fill">
      <Header></Header>
      <Message></Message>
      <div class="container mt-3">
        <div class="row">
          <div class="col-md-12">
            <router-view></router-view>
            <div
                id="reset-store-panel"
                class="card panel-warning d-noe d-sm-flex"
            ></div>
          </div>
        </div>
      </div>
    </main>
    <Footer></Footer>
  </div>
</template>
<script>
import Header from "./components/common/Header";
import Footer from "./components/common/Footer";
import Message from "./components/common/Message";

export default {
  name: "Home",
  components: {Header, Footer, Message},
  methods: {
  },
  computed: {
   
  },
  created() {
    if (this.param == undefined || this.param == null || this.param == "") {
      this.listenToProductList();
    } else {
      this.findItemByCategoryId(this.param);
    }

    },
};
</script>

<style scoped>
#reset-store-panel {
  position: fixed;
  bottom: 0px;
  right: 0px;
}

body,
.sticky-footer-wrapper {
  min-height: 100vh;
}

.flex-fill {
  flex: 1 1 auto;
}

footer {
  height: 40px;
  color: #666;
  padding: 10px 0 10px 0;
  font-size: 85%;
}

footer a {
  color: #999;
}

footer a:hover {
  color: #efefef;
}

@media (max-width: 576px) {
  footer {
    height: 50px;
  }
}
</style>