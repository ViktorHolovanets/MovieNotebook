export default {
    updateIsError(context, isError) {
        context.commit('setIsError', isError);
    },
    updateToken(context, token) {
        context.commit('setToken', token);
    },
    updateIsLoad(context, isLoad) {
        context.commit('setIsLoad', isLoad);
    },
    updateSearch(context, search) {
        context.commit('setSearch', search);
    },
    updateMyViews(context, views) {
        context.commit('setMyViews', views);
    },
    addMyView({ commit, state }, view) {
        commit('addMyView', view);
    },
    removeMyView({ commit, state }, index) {
        commit('removeMyView', index);
    },
    updateLocalStorage({ commit}) {
        commit('updateLocalStorage');
    },
};
