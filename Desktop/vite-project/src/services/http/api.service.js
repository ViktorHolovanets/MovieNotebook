import axios from 'axios';
import store from "../../stores";
import exception from "../exceptions/handlerErrorHttp";

const url = store.getters.url;
function getConfig(){
    const config = {
        headers: {
            'Authorization': `Bearer ${store.getters.getToken}`
        }
    };
    return config;
}


export async function get(path) {
    try {
        await store.dispatch('updateIsLoad', true);
        const response = await axios.get(url + path, getConfig());
        await store.dispatch('updateIsLoad', false);
        return response.data;
    } catch (error) {
        await store.dispatch('updateIsLoad', false);
        exception(error);
        throw error;
    }
}

export async function post(path, data) {
    try {
        await store.dispatch('updateIsLoad', true);
        const response = await axios.post(url + path, data, getConfig());
        await store.dispatch('updateIsLoad', false);
        return response.data;
    } catch (error) {
        await store.dispatch('updateIsLoad', false);
        exception(error);
    }
}

export async function put(path, data) {
    try {
        const response = await axios.put(url + path, data, getConfig());
        return response.data;
    } catch (error) {
        exception(error);
        throw error;
    }
}

export async function deleteRequest(path) {
    try {
        const response = await axios.delete(url + path, getConfig());
        return response.data;
    } catch (error) {
        console.log(error);
        // exception(error);
        throw error;
    }
}
