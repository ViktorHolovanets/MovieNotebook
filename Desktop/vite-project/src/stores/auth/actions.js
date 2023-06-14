export default {
    updateIsError(context, isError) {
        context.commit('setIsError', isError);
    },
    updateToken(context, token) {
        context.commit('setToken', token);
    },
    updateIsActive(context, isActive) {
        context.commit('setIsActive', isActive);
    },
    updateSearch(context, search) {
        context.commit('setSearch', search);
    },
    updateMyViews(context, views) {
        context.commit('setMyViews', views);
    },
};
