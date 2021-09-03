const state = {
	cartItemList: [

	],
	compareItemList: [

	]
};

const mutations = {
	'UPDATE_CART'(state, { item }) {
		const record = state.cartItemList.find(element => element.id == item.id);
		if (!record) {
			state.cartItemList.push({
				...item,
			});
		}
	},
	'SET_CART'(state, cartItemList) {
		if (cartItemList) {
			state.cartItemList = cartItemList;
		}
	},
	'REMOVE_CART_ITEM'(state, { item }) {
		const record = state.cartItemList.find(element => element.id == item.id);
		state.cartItemList.splice(state.cartItemList.indexOf(record), 1);
	},
	'COMPARE_CART_ITEM'(state, { item }) {
		const record = state.compareItemList.find(element => element.id == item.id);
		if (!record) {
			state.compareItemList.push({
				...item,
			});
		}
	},
};

const actions = {
	clearCart: ({ commit }) => {
		commit('SET_CART', []);
	},
};

const getters = {
	cartItemList: (state) => {
		return state.cartItemList;
	},
	compareItemList: (state) => {
		return state.compareItemList;
	}
};

export default {
	state,
	mutations,
	actions,
	getters
}
