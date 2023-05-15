
import store from '../../stores/index';
import { post } from "../../services/http/api.service";


const config = {
    headers: {
        'Authorization': `Bearer ${store.getters['getToken']}`
    }
};

export function _register(data) {
    return new Promise((resolve, reject) => {
        return resolve(post(store.getters['register'], data))
    })
}

export function _login(data) {
    return new Promise((resolve, reject) => {
        resolve(post(store.getters['login'], data))
    })
}
