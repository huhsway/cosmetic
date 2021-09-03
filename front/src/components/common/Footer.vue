<template>
  <footer class="navbar-default navbar-bottom navbar-light bg-light">
    <div class="container-fluid">
      <p class="text-center nav-bar mb-0">cosmetic</p>
    </div>
  </footer>
</template>
<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
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