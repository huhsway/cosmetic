<template>
  <tr>
    <td class="actions" data-th="">
    </td>
    <td data-th="Product">
      <div class="row">
        <div class="col-sm-2 d-none d-sm-block">
          <img :src="cartItem.image" alt="..." class="img-fluid" />
        </div>
        <div class="col-sm-10">
          <h4 class="nomargin">{{ cartItem.title }}</h4>
          <p><a v-bind:href="cartItem.link">링크이동</a></p>
        </div>
      </div>
    </td>
    <td class="actions" data-th="">
      <button class="btn btn-danger btn-sm" @click="removeItem">
        <i class="fa fa-trash-o"></i>
      </button>
    </td>
  </tr>
</template>

<script>
import { mapActions } from "vuex";
export default {
  props: ["cartItem"],
  methods: {
    ...mapActions(["updateCart", "removeItemInCart"]),
    removeItem() {
      this.removeItemInCart({
        item: this.cartItem,
      });
    },
  },
};
</script>
