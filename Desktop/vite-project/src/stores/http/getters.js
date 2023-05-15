export default {
    isError(state) {
        return state.isError;
    },
    url(state) {
        return state.url;
    },
    login(state) {
        return state.login;
    },
    register(state) {
        return state.register;
    },
    search(state) {
        return state.search;
    },
    STATE_MESSAGE: (state) => state.errorMessage,
    STATE_ERROR: (state) => state.isError,
};
