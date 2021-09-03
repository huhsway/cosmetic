const state = {
	heartItemList: [

	]
};

const mutations = {
	'UPDATE_HEART'(state, { item }) {
		const record = state.heartItemList.find(element => element.id == item.id);
		if (!record) {
			state.heartItemList.push({
				...item,
			});
		}
	},
	'SET_HEART'(state, heartItemList) {
		if (heartItemList) {
			state.heartItemList = heartItemList;
		}
	},
	'REMOVE_HEART_ITEM'(state, { item }) {
		const record = state.heartItemList.find(element => element.id == item.id);
		state.heartItemList.splice(state.heartItemList.indexOf(record), 1);
	}
};

const actions = {
	clearHeart: ({ commit }) => {
		commit('SET_HEART', []);
	},
};

const getters = {
	heartItemList: (state) => {
		return state.heartItemList;
	},
};

export default {
	state,
	mutations,
	actions,
	getters
}
