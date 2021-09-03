<template>
	<div class="mb-3 col-sm-6 col-md-4 item" :class="{ 'list-group-item': displayList }">
		<div class="thumbnail card text-center">
			<router-link :to="'/item/' + heartItem.product_id" tag="h5" class="card-title">
				<a>
					<div class="img-event intrinsic">
						<img
								:src="heartItem.image"
								alt=""
								class="grow thumbnail-image card-img-top intrinsic-item p-3"
						/>
					</div>
					{{ heartItem.title }}
				</a>
			</router-link>
			<div class="card-body">
				<!-- <p class="card-text truncate">{{ item.description | shortDescription}}</p> -->
				<p class="card-text">
					<a v-bind:href="heartItem.link">상품 구매 페이지 이동</a>
				</p>
			</div>
			<!-- Item Footer -->
			<div class="card-footer">
				<div class="row">
					<div class="col-6 align-self-center">
						{{ heartItem.lprice }}~{{ heartItem.hprice }}원
					</div>
					<div class="col-6">
						<button class="btn btn-primary" type="button" @click="addItem">
							비교 상품 담기
						</button>
						<button class="btn btn-danger btn-sm" @click="removeItem">
							<i class="fa fa-trash-o"></i>
						</button>
					</div>
				</div>
			</div>
			<!-- /.Item Footer -->
		</div>
	</div>
</template>

<script>
import { mapActions } from "vuex";
export default {
	props: ["heartItem", "displayList"],
	computed:{

	},
	methods: {
		...mapActions(["updateHeart", "removeItemInHeart"]),
		removeItem() {
			this.removeItemInHeart({
				item: this.heartItem,
			});
		},
		...mapActions(["updateCart"]),
		addItem() {
			const order = {
				item: Object.assign({}, this.heartItem),
				isAdd: true,
			};
			this.updateCart(order);
		}
	}
};
</script>

<style lang="scss" scoped>
div.card {
	height: 100%;
}

.card-text {
	font-size: 0.875rem;
}

.remain {
	color: #d17581;
}

.grow {
	transition: all 0.2s ease-in-out;
}

.grow:hover {
	transform: scale(1.1);
}

.list-group-item {
	float: none;
	width: 100%;
	background-color: #fff;
	margin-bottom: 30px;
	-ms-flex: 0 0 100%;
	flex: 0 0 100%;
	padding: 0 1rem;
	border: 0;

	.thumbnail {
		display: inline-block;
		width: 100%;
	}

	.img-event {
		width: 20%;
		float: left;
		padding: 0 !important;
		margin: 0;
		height: auto;
	}

	.thumbnail-image {
		position: static;
	}

	.card-body {
		float: left;
		width: 80%;
		margin: 0;
	}

	@media (max-width: 767.98px) {
		.img-event {
			width: 30%;
			float: left;
			padding: 0 !important;
			margin: 0;
		}

		.card-body {
			float: left;
			width: 70%;
			margin: 0;
		}
	}
}

.item.list-group-item:before,
.item.list-group-item:after {
	display: table;
	content: " ";
}

.item.list-group-item:after {
	clear: both;
}
</style>
