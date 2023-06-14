export default {
    setIsError(state, isError) {
        state.isError = isError;
    },

    setSearch(state, search) {
        state.resultSearch = search;
    },
    SET_ERROR(state, message) {
        state.isError = true;
        state.errorMessage = message;
    },
    RESET_ERROR(state) {
        state.isError = false;
        state.errorMessage = '';
    },
};
