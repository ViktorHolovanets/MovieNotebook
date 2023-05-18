import { createStore } from 'vuex';
import state from './state';
import mutations from './mutations';
import actions from './actions';
import getters from "./getters";




const appStore= {
    state,
    mutations,
    actions,
    getters,
};
export default appStore;