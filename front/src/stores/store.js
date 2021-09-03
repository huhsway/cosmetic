import Vue from "vue";
import Vuex from "vuex";
import member from "./modules/member";
import messages from "./modules/messages";
import * as actions from "./actions";
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
	actions,
	modules: {
		member,
		messages,
	},
	plugins: [
		createPersistedState()
	]
});
