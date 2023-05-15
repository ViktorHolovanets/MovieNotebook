export default {
    updateMyViews(context, myViews) {
        context.commit('setMyViews', myViews);
    },
    updateToken(context, token) {
        context.commit('setToken', token);
    },
    updateIsActive(context, isActive) {
        context.commit('setIsActive', isActive);
    },
    visibility_error(context, message) {
        context.commit('SET_ERROR', message);
    },
    hidden_error(context) {
        context.commit('RESET_ERROR');
    },
};
