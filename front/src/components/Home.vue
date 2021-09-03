<template>
  <div :class="{ loadingItem: isItemLoading }" class="container">
    <div v-if="isItemLoading" class="text-center">
      <grid-loader
          :color="loaderColor"
          :loading="isItemLoading"
          :size="loaderSize"
      ></grid-loader>
    </div>
    <div v-else class="row action-panel">
      <div class="col-12 align-self-end">
        <div class="btn-group btn-group-sm pull-left">
          <base-input v-model="searchInput"></base-input>
          <!--          <mdb-input v-model="searchInput" class="mt-0" label="" type="text"/>-->
          <button id="searchButton" class="btn btn-primary" type="button" @click="findItemsByItemNameContains">검색
          </button>
        </div>
        <div class="btn-group btn-group-sm pull-right">
          <button
              id="list"
              class="btn btn-outline-dark"
              @click.prevent="changeDisplay(true)"
          >
            <i aria-hidden="true" class="fa fa-list"></i> List
          </button>
          <button
              id="grid"
              class="btn btn-outline-dark"
              @click.prevent="changeDisplay(false)"
          >
            <i aria-hidden="true" class="fa fa-th"></i> Grid
          </button>
        </div>
      </div>
    </div>
    <!-- Item List -->
    <!--    <div class="stick-top">control box</div>-->
    <div v-if="!isItemLoading" class="row">
      <item
          v-for="prod in items"
          :key="prod.id"
          :displayList="displayList"
          :item="prod"
      />
      <infinite-loading spinner="waveDots" @infinite="infiniteHandler">
        <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">목록의 끝입니다 :)</div>
      </infinite-loading>
    </div>
    <!-- /.Item List -->
  </div>
</template>

<script>
import {mapActions} from "vuex";
import GridLoader from "vue-spinner/src/GridLoader.vue";
import BaseInput from "@/components/util/BaseInput";
import InfiniteLoading from 'vue-infinite-loading';
import axios from "axios";

export default {
  name: "mdbSearchEcample",
  data() {
    return {
      loaderColor: "#5cb85c",
      loaderSize: "50px",
      displayList: false,
      searchInput: '',
      offset: 30,
    };
  },
  computed: {
  },
  components: {
    GridLoader,
    'base-input': BaseInput,
    InfiniteLoading,
  },
  methods: {
    ...mapActions(['findItemsByItemName']),
    changeDisplay(isList) {
      this.displayList = isList;
    },
    findItemsByItemNameContains() {
      let searchInput = this.searchInput;
      if (searchInput != null && searchInput != undefined && searchInput != "") {
        this.findItemsByItemName(this.searchInput);
      }
    },
    infiniteHandler($state) {
      axios.get(`http://ec2-3-36-83-107.ap-northeast-2.compute.amazonaws.com:8081/api/v1/items?offset=` + this.offset)
          .then((response) => {
            setTimeout(() => {
              console.log(response.data.length);
              if (response.data.length) {
                this.$store.commit('ADD_PRODUCT_LIST', response.data);
                this.offset += 30;
                $state.loaded();
              } else {
                $state.complete();
              }
            }, 1000);
          })
          .catch((error) => {
            console.log(error);
          });
    },
  }
};
</script>

<style>
.loadingItem {
  align-items: center;
  justify-content: center;
  display: flex;
}

.action-panel {
  margin-bottom: 10px;
  margin-right: 5px;
}

#searchButton {
  margin-left: 10px;
}
</style>