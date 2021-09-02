import ApiService from "@/apis/api";

const BASE_URL = '/api/v1/items';
const COSMETICS_BASE_URL = '/api/v1/items/cosmetics';

const ItemService = {
    findItems(offset, limit) {
        return ApiService.get(`${BASE_URL}?offset=${offset}&limit=${limit}`);
    },
    findItemById(itemId) {
        return ApiService.get(`${BASE_URL}/${itemId}`);
    },
    findCosmeticsById(itemId) {
        return ApiService.get(`${COSMETICS_BASE_URL}/${itemId}`);
    }
};
export default ItemService;