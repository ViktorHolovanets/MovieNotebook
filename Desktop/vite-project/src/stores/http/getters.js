export default {
    isError(state) {
        return state.isError;
    },
    url(state) {
        return state.url;
    },
    login(state) {
        return state.login_url;
    },
    register(state) {
        return state.register_url;
    },
    getSearch(state) {
        return state.search_url;
    },
    getAddMovie(state) {
        return state.addMovie_url;
    },
    getInfoMovie(state) {
        return state.infoMovie_url;
    },
    getUpMovie(state) {
        return state.updateMovie_url;
    },
    getViews(state) {
        return state.myViews_url;
    },
    STATE_MESSAGE: (state) => state.errorMessage,
    STATE_ERROR: (state) => state.isError,
};
