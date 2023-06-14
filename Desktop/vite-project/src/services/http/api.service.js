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
        const response = await axios.get(url + path, getConfig());
        return response.data;
    } catch (error) {
        exception(error);
        throw error;
    }
}

export async function post(path, data) {
    try {
        const response = await axios.post(url + path, data, getConfig());
        console.log(response.data)
        return response.data;
    } catch (error) {
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
