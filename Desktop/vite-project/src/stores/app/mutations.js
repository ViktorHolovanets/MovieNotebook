export default {
    setFilter(state, isOpen) {
        state.isOpenFilter = isOpen;
    },
    setSearch(state, search) {
        state.resultSearch = search;
    },
    setParamTitle(state, title){
        state.params.search=title;
    },
    setParamPage(state, page){
        state.params.page=page;
    },
    setParamYear(state, year){
        state.params.year=year;
    },
    setParamType(state, type){
        state.params.type=type;
    },
};
