const getters= {
    getFilter(state) {
        return state.isOpenFilter;
    },
    getSearchResult(state) {
        return state.resultSearch;
    },
    getParams(state) {
        return state.params;
    },
};
export default getters