export default {

    visibility_error(context, message) {
        context.commit('SET_ERROR', message);
    },
    hidden_error(context) {
        context.commit('RESET_ERROR');
    },
};
