
import store from '../../stores/index';
import { post } from "../../services/http/api.service";


const config = {
    headers: {
        'Authorization': `Bearer ${store.getters['getToken']}`
    }
};

export function _register(data) {
    return new Promise((resolve, reject) => {
        return resolve(post(store.getters.register, data))
    })
}

export function _login(data) {
    return new Promise((resolve, reject) => {
        resolve(post(store.getters.login, data))
    })
}
export function _addMovie(data) {
    return new Promise((resolve, reject) => {
        resolve(post(store.getters.getAddMovie, data))
    })
}
export function _infoMovie(data) {
    return new Promise((resolve, reject) => {
        resolve(post(store.getters.getInfoMovie, data, config))
    })
}
export function _updateMovie(data) {
    return new Promise((resolve, reject) => {
        resolve(post(store.getters.getUpMovie, data, config))
    })
}
export function _search(data) {
    return new Promise((resolve, reject) => {
        resolve(post(store.getters.getSearch, data))
    })
}
