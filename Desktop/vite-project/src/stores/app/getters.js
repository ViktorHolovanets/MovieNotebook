const getters= {
    getFilter(state) {
        return state.isOpenFilter;
    },
    getSearchResult(state) {
        return state.resultSearch;
    },
};
export default getters