export default {
    setIsError(state, isError) {
        state.isError = isError;
    },
    setUrl(state, url) {
        state.url = url;
    },
    setLogin(state, login) {
        state.login = login;
    },
    setRegister(state, register) {
        state.register = register;
    },
    setSearch(state, search) {
        state.search = search;
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
