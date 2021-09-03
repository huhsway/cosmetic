const state = {
	isItemLoading: false,
	items: [],
};

const mutations = {
	'UPDATE_PRODUCT_LIST'(state, items) {
		state.items = items;
		state.isItemLoading = false;
	},
	'ADD_PRODUCT_LIST'(state,items){
		state.items = state.items.concat(items);
	}
}

const actions = {

}

const getters = {
	items: (state) => {
		return state.items;
	},
	isItemLoading: (state) => {
		return state.isItemLoading;
	}
}

export default {
	state,
	mutations,
	actions,
	getters
}