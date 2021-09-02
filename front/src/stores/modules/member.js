import {requestJoinMember, requestLoginMember} from "../../apis/member_api"
import {deleteTokenInLocalStorage, setTokenInLocalStorage} from "../../utils/auth";
import {deleteAccessTokenInHeader} from "../../apis/common_api";
import {routes} from "../../routes";

const state = {
    accessToken: localStorage.getItem('auth_token'),
    isAuthenticated: setTokenInLocalStorage != null,
};

const getters = {
    isAuthenticated(state) {
        return !!state.accessToken;
    },
};

function resetToken(state) {
    deleteTokenInLocalStorage();
    deleteAccessTokenInHeader();
    state.accessToken = null;
    state.isAuthenticated = null;
}

const mutations = {
    LOGIN(state) {
        state.accessToken = localStorage.getItem('auth_token');
        routes.push('/');
    },
    LOGOUT(state) {
        return resetToken(state);
    },
    LOGOUT_WITH_TOKEN_INVALIDE(state) {
        resetToken(state);
        routes.push('/login')
        // if (routes.currentRoute.name !== 'cart') {
        //     routes.push('/');
        // }
    }
};

const actions = {
    async requestJoin(context, member) {
        const response = await requestJoinMember(member);
        return response;
    },
    async requestLogin(context, member) {
        const response = await requestLoginMember(member);
        setTokenInLocalStorage(response.data.auth_token);
        context.commit('LOGIN', response.data);
    },
};

export default {mutations, state, actions, getters};