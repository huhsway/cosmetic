import axios from "axios";

const EC2_URL = "http://ec2-3-36-83-107.ap-northeast-2.compute.amazonaws.com:8081/api";
// const DAOU_URL = "http://172.22.1.216:8081/api";

export function listenToProductList({commit}) {
    axios.get(`${EC2_URL}/v1/items?itemCategoryId=50000002`)
        .then(function (response) {
            commit('UPDATE_PRODUCT_LIST', response.data);
        });
}

export function findItemByCategoryId({commit}, props) {
    console.log(props);
    axios.get(`${EC2_URL}/v1/items/categories/${props}`).then(response => {
        commit('UPDATE_PRODUCT_LIST', response.data);
    });
}

export function findItemsByItemName({commit}, props) {
    let decodedUrl = decodeURI(`${EC2_URL}/v1/items/search?title=${props}`);
    axios.get(decodedUrl).then(response => {
        commit('UPDATE_PRODUCT_LIST', response.data);
    });
}

export const updateCart = ({commit}, {item, isAdd}) => {
    commit('UPDATE_CART', {item, isAdd});
    if (isAdd) {
        let message_obj = {
            message: `${item.title}을 상품비교 카트에 담았습니다.`,
            messageClass: "success",
            autoClose: true
        }
        commit('ADD_MESSAGE', message_obj);
    }
}

export const removeItemInCart = ({commit}, {item}) => {
    commit('REMOVE_CART_ITEM', {item});
}