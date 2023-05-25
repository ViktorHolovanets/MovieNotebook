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
    getSearch(state) {
        return state.search;
    },
    getAddMovie(state) {
        return state.addMovie;
    },
    getInfoMovie(state) {
        return state.infoMovie;
    },
    getUpMovie(state) {
        return state.updateMovie;
    },
    STATE_MESSAGE: (state) => state.errorMessage,
    STATE_ERROR: (state) => state.isError,
};
