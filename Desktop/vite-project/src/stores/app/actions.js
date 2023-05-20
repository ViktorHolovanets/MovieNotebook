export default {
    updateIsFilter(context, isOpen) {
        context.commit('setFilter', isOpen);
    },
    updateSearchResult(context, search) {
        context.commit('setSearch', search);
    },
};
