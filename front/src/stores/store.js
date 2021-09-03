import Vue from "vue";
import Vuex from "vuex";
import cart from "./modules/cart";
import items from "./modules/items";
import member from "./modules/member";
import messages from "./modules/messages";
import heart from "./modules/heart"
import * as actions from "./actions";
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
	actions,
	modules: {
		cart,
		member,
		items,
		heart,
		messages,
	},
	plugins: [
		createPersistedState()
	]
});
