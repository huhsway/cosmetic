import axios from 'axios';

const BASE_URL = 'http://ec2-3-36-83-107.ap-northeast-2.compute.amazonaws.com:8081';

const ApiService = {
    get(uri) {
        return axios.get(`${BASE_URL}` + uri);
    },
    post(uri, params) {
        return axios.post(`${BASE_URL}${uri}`, params);
    }
}

export default ApiService;