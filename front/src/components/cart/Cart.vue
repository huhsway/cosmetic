<template>
  <div class="container table-responsive">
    <table id="cart" class="table table-hover table-sm">
      <thead>
      <tr>
        <th style="width: 1%"></th>
        <th style="width: 89%">Product</th>
        <th style="width: 10%"></th>
      </tr>
      </thead>

      <transition-group name="list-shopping-cart" tag="tbody">
        <app-cart-item
            v-for="cartItem in cartItemList"
            :cartItem="cartItem"
            :key="cartItem.id"
        ></app-cart-item>
      </transition-group>

      <tfoot>
      <tr>
        <td></td>
        <td>
        </td>
        <td colspan="2" class="d-none d-sm-table-cell"></td>
        <td class="px-0">
          <button class="btn btn-success" @click.prevent="compareItem()">
            <span class="text-nowrap">상품 비교하기</span>
          </button>
        </td>
      </tr>
      </tfoot>
    </table>
    <!-- Item Compare -->
    <div class="wrapper"  v-if="isCompare">
      <item-compare-details
          v-for="prod in cartItemList"
          :item="prod"
          :key="prod.id"
          :displayList="false"
      />
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import ItemCompareDetails from "../item/ItemCompareDetails.vue";
import CartItem from "./component/CartItem.vue";
export default {
  data() {
    return {
      isCompare: false,
    };
  },
  computed: {
    ...mapGetters([
      "cartItemList",
      "isAuthenticated",
      "items",
    ]),
  },
  components: {
    appCartItem: CartItem,
    ItemCompareDetails,
  },
  methods: {
    ...mapActions(["addMessage", "clearCart"]),
    checkValidCart() {
      let isValid = true;
      let message = "";
      return {
        isValid,
        message,
      };
    },
    compareItem() {
      this.isCompare = !this.isCompare;
    },
  },
};
</script>

<style lang="scss" scoped>
.list-shopping-cart-leave-active {
  transition: all 0.4s;
}

.list-shopping-cart-leave-to {
  opacity: 0;
  transform: translateX(50px);
}

.table-sm {
  font-size: 0.875rem;
  ::v-deep h4 {
    font-size: 1.25rem;
  }
}

.wrapper {
  display: grid;
  grid-template-columns: repeat(2, auto);
  grid-template-rows: repeat(2, auto);
}
</style>