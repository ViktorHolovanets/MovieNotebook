import { createStore } from 'vuex';
import authStore from "./auth/authStore";
import httpStore from "./http/httpStore";


export default createStore({
    modules: {
        authStore,
        httpStore,
    },
});