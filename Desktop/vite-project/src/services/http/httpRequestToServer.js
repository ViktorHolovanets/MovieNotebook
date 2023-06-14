import store from '../../stores/index';
import { post } from "../../services/http/api.service";



export async function _register(data) {
    try {
        const response = await post(store.getters.register, data);
        return response;
    } catch (error) {
        throw error;
    }
}

export async function _login(data) {
    try {
        const response = await post(store.getters.login, data);
        return response;
    } catch (error) {
        throw error;
    }
}

export async function _addMovie(data) {
    try {
        const response = await post(store.getters.getAddMovie, data);
        return response;
    } catch (error) {
        throw error;
    }
}

export async function _infoMovie(data) {
    try {
        const response = await post(store.getters.getInfoMovie, data);
        return response;
    } catch (error) {
        throw error;
    }
}

export async function _updateMovie(data) {
    try {
        const response = await post(store.getters.getUpMovie, data);
        return response;
    } catch (error) {
        throw error;
    }
}

export async function _search(data) {
    try {
        const response = await post(store.getters.getSearch, data);
        return response;
    } catch (error) {
        throw error;
    }
}

export async function _getViews() {
    try {
        const response = await post(store.getters.getViews);
        await store.dispatch('updateMyViews', response??[]);
        localStorage.setItem('views',JSON.stringify(response)??[])
        return response;
    } catch (error) {
        throw error;
    }
}
