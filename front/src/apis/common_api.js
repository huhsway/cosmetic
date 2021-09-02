import axios from "axios";
import store from "@/stores/store";
import { routes } from "@/routes";

/*
    모든 요청 전 header에 auth_token을 담아 전송한다.
 */
axios.interceptors.request.use(
    config => {
        let accessToken = localStorage.getItem('auth_token');
        if (accessToken !== null) {
            config.headers.common["jwt-auth-token"]  = accessToken;
        }
        console.log('Interceptors Request is', config, new Date());
        return config
    },
    error => {
        console.log('Interceptors Request Error is', error.response, new Date());
        return Promise.reject(error);
    }
);

/*
    만료된 auth_token으로 요청시 Access token exprited가 발생하는 경우
 */
axios.interceptors.response.use(
    response => {
        console.log('Interceptors Response is ', response, new Date());
        return response;
    },
    function (error) {
        console.log('Interceptors Response Error is ', error.response, new Date());

        if (!error.response) {
            routes.push('/');
        }

        let status = error.response.status;
        if (status === 500) {
            store.commit('PUSH_ERROR_PAGE');
            return Promise.reject(error);
        }

        if (status === 401) {
            let errorData = error.response.data;
            if (errorData.error !== 'invalid_token') {
                return Promise.reject(error);
            }

            if (isExpiredToken(errorData)) {
                store.commit('LOGOUT_WITH_TOKEN_INVALIDE');
                // 메시지 오류 처리 예정
            }
        }

        return Promise.reject(error);
    }
);

function isExpiredToken(errorData) {
    let errorDescription = errorData.error_description;
    return errorDescription.substring(0, 20) === 'Access token expired';
}

const deleteAccessTokenInHeader = () => {
    axios.defaults.headers.common["jwt-auth-token"] = null;
};

export {
    deleteAccessTokenInHeader,
}

