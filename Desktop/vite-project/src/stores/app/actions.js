export default {
    updateIsFilter(context, isOpen) {
        context.commit('setFilter', isOpen);
    },
    updateSearchResult(context, search) {
        context.commit('setSearch', search);
    },
    updateParamTitle(context, title) {
        context.commit('setParamTitle', title);
    },
    updateParamYear(context, year) {
        context.commit('setParamYear', year);
    },
    updateParamType(context, type) {
        context.commit('setParamType', type);
    },
    updateParamPage(context, page) {
        context.commit('setParamPage', page);
    },
};
