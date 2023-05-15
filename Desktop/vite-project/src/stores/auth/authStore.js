import { createStore } from 'vuex';
import state from './state';
import mutations from './mutations';
import actions from './actions';


const getters= {
    getMyViews:(state) =>{
        return state.myViews;
    },
    getToken:(state)=> {
        return state.token;
    },
    getIsActive:(state)=> {
        return state.isActive;
    },
};

const authStore= {
    state,
    mutations,
    actions,
    getters,
};
export default authStore;