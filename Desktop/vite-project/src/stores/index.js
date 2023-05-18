import { createStore } from 'vuex';
import authStore from "./auth/authStore";
import httpStore from "./http/httpStore";
import appStore from "./app/appStore";


export default createStore({
    modules: {
        authStore,
        httpStore,
        appStore,
    },
});