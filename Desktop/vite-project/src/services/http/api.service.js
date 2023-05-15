import axios from 'axios';
import store from "../../stores";
import exception from "../exceptions/handlerErrorHttp";

const url = store.getters['url'];

export async function get(path, config) {
    return new Promise(async (resolve, reject) => {
        await axios.get(url + path, config).then(response => {
            resolve(response.data)
        }).catch(ex => {
            exception(ex);
        });

    })
}

export async function post(path, data, config) {
    return new Promise(async (resolve, reject) => {
        await axios.post(url + path, data, config).then(response => {
            resolve(response.data)
        }).catch(ex => {
            resolve(exception(ex));
        });

    })
}


export async function put(path, data, config) {
    return new Promise(async (resolve, reject) => {
        await axios.put(path, data, config).then(response => {
            resolve(response.data)
        }).catch(ex => {
            resolve(exception(ex));
        });

    })
}


export async function deleteRequest(path, config) {
    return new Promise(async (resolve, reject) => {
        axios.delete(path, config).then(response => {
            resolve(response.data)
        }).catch(ex => {
            console.log(ex);
            // resolve(exception(ex));
        });

    })
}

