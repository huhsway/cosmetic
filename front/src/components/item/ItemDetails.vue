<template>
  <div class="container">
    <div class="col-md-12">
      <div v-if="isItemLoading" class="text-center"></div>
      <div v-else class="card">
        <div class="row">
          <div class="col-12 col-md-4 offset-md-4">
            <div class="intrinsic">
              <img :src="item.image" alt="" class="img-fluid intrinsic-item"/>
            </div>
          </div>
        </div>
        <!-- 상품 정보 영역 -->
        <div class="caption-full">
          <h4 class="pull-right">{{ item.lprice }}~{{ item.hprice }}원</h4>
          <h4>{{ item.title }}</h4>
          <p><a v-bind:href="item.link">상품 구매 페이지 이동</a></p>

          <!-- 평판(평점, 별점 그래프, 리뷰) -->
          <div class="container">
            <div class="row">
              <div class="col-xs-12 col-md-6">
                <div class="well well-sm">
                  <div class="row">
                    <div class="col-xs-12 col-md-6 text-center">
                      <h1 class="rating-num">{{this.item.reputation.totalStarRatio}}</h1>
                      <div class="rating">
                        <span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star">
                            </span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star">
                            </span><span class="glyphicon glyphicon-star-empty"></span>
                      </div>
                      <div>
                        <span class="glyphicon glyphicon-user"></span>{{this.item.reputation.totalReviewCount}} total
                      </div>
                    </div>
                    <div class="col-xs-12 col-md-6">
                      <div class="row rating-desc">
                        <div class="col-xs-3 col-md-3 text-right">
                          <span class="glyphicon glyphicon-star"></span>5
                        </div>
                        <div class="col-xs-8 col-md-9">
                          <div class="progress progress-striped">
                            <div class="progress-bar progress-bar-five" role="progressbar" aria-valuenow="20"
                                 aria-valuemin="0" aria-valuemax="100" :style="{width: this.item.reputation.fiveStarRatio+'%'}">
                              <span class="sr-only">{{this.item.reputation.fiveStarRatio}}%</span>
                            </div>
                          </div>
                        </div>
                        <!-- end 5 -->
                        <div class="col-xs-3 col-md-3 text-right">
                          <span class="glyphicon glyphicon-star"></span>4
                        </div>
                        <div class="col-xs-8 col-md-9">
                          <div class="progress">
                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                 aria-valuemin="0" aria-valuemax="100" :style="{width: this.item.reputation.fourStarRatio+'%'}">
                              <span class="sr-only">{{this.item.reputation.fourStarRatio}}%</span>
                            </div>
                          </div>
                        </div>
                        <!-- end 4 -->
                        <div class="col-xs-3 col-md-3 text-right">
                          <span class="glyphicon glyphicon-star"></span>3
                        </div>
                        <div class="col-xs-8 col-md-9">
                          <div class="progress">
                            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20"
                                 aria-valuemin="0" aria-valuemax="100" :style="{width: this.item.reputation.threeStarRatio+'%'}">
                              <span class="sr-only">{{this.item.reputation.threeStarRatio}}%</span>
                            </div>
                          </div>
                        </div>
                        <!-- end 3 -->
                        <div class="col-xs-3 col-md-3 text-right">
                          <span class="glyphicon glyphicon-star"></span>2
                        </div>
                        <div class="col-xs-8 col-md-9">
                          <div class="progress">
                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20"
                                 aria-valuemin="0" aria-valuemax="100" :style="{width: this.item.reputation.twoStarRatio+'%'}">
                              <span class="sr-only">{{this.item.reputation.twoStarRatio}}%</span>
                            </div>
                          </div>
                        </div>
                        <!-- end 2 -->
                        <div class="col-xs-3 col-md-3 text-right">
                          <span class="glyphicon glyphicon-star"></span>1
                        </div>
                        <div class="col-xs-8 col-md-9">
                          <div class="progress">
                            <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80"
                                 aria-valuemin="0" aria-valuemax="100" :style="{width: this.item.reputation.oneStarRatio+'%'}">
                              <span class="sr-only">{{this.item.reputation.oneStarRatio}}%</span>
                            </div>
                          </div>
                        </div>
                        <!-- end 1 -->
                      </div>
                      <!-- end row -->
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <tr class="space" v-for="(review, index1) in item.reputation.reviews" :key="index1">
            <td>{{ review.title }}: {{ review.content }}</td>
          </tr>

          <p class="pull-right">
            <button @click="addItem" class="btn btn-success">
              비교 상품 담기
            </button>
            <button class="btn btn-info" type="button" @click="addHeart">
              찜
            </button>
          </p>

          <!-- 상품 상세 정보 영역 -->
          <table class="table">
            <!-- detail -->
            <tr v-for="(detail, key) in details" :key="key">
              <th v-if="key !== 'ingredients'">{{ field[key] }}</th>
              <td v-if="key !== 'ingredients'">
                {{ detail }}
              </td>
            </tr>
            <!-- /.detail -->
            <!-- ingredient -->
            <tr>
              <th>{{ field["ingredients"] }}</th>
              <td>
                <tr v-for="(ingredient, key) in details.ingredients" :key="key">
                  <td scope="col">{{ ingredient.name }}</td>
                  <td>
                    <tr v-for="(feature, key) in ingredient.features" :key="key">
                      <td scope="row">{{ feature.name }}</td>
                      <td v-if="feature.status === 'Warning'" class="table-danger">{{ feature.status }}</td>
                      <td v-if="feature.status !== 'Warning'" class="table-success">{{ feature.status }}</td>
                    </tr>
                  </td>
                </tr>
              </td>
            </tr>
            <!-- /.ingredient -->
          </table>
          <!-- /.상품 상세 정보 영역 -->
          <div class="clearfix"></div>
        </div>
        <!-- /.상품 상세 정보 영역 -->
      </div>
    </div>
  </div>
</template>
<script>
import {mapActions, mapGetters} from "vuex";
import ItemService from "@/apis/module/items";

export default {
  data() {
    return {
      loaderColor: "#5cb85c",
      loaderSize: "50px",
      details: [],
      field: {
        'skinType': "피부타입",
        'shape': "형태",
        'useArea': "사용부위",
        'volume': "용량",
        'mainFeature': "주요제품특징",
        'detailFeature': "세부제품특징",
        'useTime': "사용시간",
        'paRating': "PA 지수",
        'sunBlockRating': "자외선 차단지수",
        'color': "색상",
        'effect': "연출 효과",
        'type': "타입",
        'ingredients': "성분",
      }
    };
  },
  created() {
    ItemService.findCosmeticsById(this.$route.params.id)
        .then((response) => {
          this.details = response.data;
          console.log(this.item.reputation.totalStarRatio)
        })
        .catch();
  },
  computed: {
    ...mapGetters(["isItemLoading", "items"]),
    item() {
      let id = this.$route.params.id;
      if (this.items.length) {
        let filterArr = this.items.filter((item) => {
          return item.id == id;
        });
        if (filterArr.length > 0) {
          return filterArr[0];
        }
      }
      return {};
    },
  },
  methods: {
    ...mapActions(["updateCart", "updateHeart"]),
    addItem() {
      const order = {
        item: Object.assign({}, this.item),
        isAdd: true,
      };
      this.updateCart(order);
    },
    addHeart() {
      const order = {
        item: Object.assign({}, this.item),
        isAdd: true,
      };
      this.updateHeart(order);
    },
  },
};
</script>

<style scoped>
.caption-full {
  padding-right: 10px;
  padding-left: 10px;
}


body{ margin-top:20px;}
.glyphicon { margin-right:5px;}
.rating .glyphicon {font-size: 22px;}
.rating-num { margin-top:0px;font-size: 54px; }
.progress { margin-bottom: 5px;}
.progress-bar { text-align: left; }
.rating-desc .col-md-3 {padding-right: 0px;}
.sr-only { margin-left: 5px;overflow: visible;clip: auto; }

</style>
