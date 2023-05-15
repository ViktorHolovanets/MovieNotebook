export default {
    updateIsError(context, isError) {
        context.commit('setIsError', isError);
    },
    updateUrl(context, url) {
        context.commit('setUrl', url);
    },
    updateLogin(context, login) {
        context.commit('setLogin', login);
    },
    updateRegister(context, register) {
        context.commit('setRegister', register);
    },
    updateSearch(context, search) {
        context.commit('setSearch', search);
    },
};
