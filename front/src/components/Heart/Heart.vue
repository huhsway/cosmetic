<template>
	<div class="container" :class="{ loadingItem: isItemLoading }">
		<div v-if="isItemLoading" class="text-center">
			<grid-loader
					:loading="isItemLoading"
					:color="loaderColor"
					:size="loaderSize"
			></grid-loader>
		</div>
		<div v-else class="row action-panel">
			<div class="col-12 align-self-end">
				<div class="btn-group btn-group-sm pull-right">
					<button
							id="list"
							class="btn btn-outline-dark"
							@click.prevent="changeDisplay(true)"
					>
						<i class="fa fa-list" aria-hidden="true"></i> List
					</button>
					<button
							id="grid"
							class="btn btn-outline-dark"
							@click.prevent="changeDisplay(false)"
					>
						<i class="fa fa-th" aria-hidden="true"></i> Grid
					</button>
				</div>
			</div>
		</div>
		<div class="blank"></div>
		<h2>찜한 상품</h2>
		<div class="row" v-if="!isItemLoading">
			<app-heart-item
					v-for="heartItem in heartItemList"
					:heartItem="heartItem"
					:key="heartItem.id"
					:displayList="displayList"
			></app-heart-item>
		</div>
	</div>
</template>

<script>
import { mapGetters } from "vuex";
import HeartItem from "./HeartItem";

export default {
	data() {
		return {
			loaderColor: "#5cb85c",
			loaderSize: "50px",
			displayList: false,
		};
	},
	computed: {
		...mapGetters(["items", "isItemLoading", "heartItemList"]),
	},
	components: {
		appHeartItem: HeartItem
	},
	methods: {
		changeDisplay(isList) {
			this.displayList = isList;
		},
	},
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

.blank{
	margin: 50px;
}

.userId{
	margin-top: 10px;
}
</style>
