export default {
    setMyViews(state, myViews) {
        state.myViews = myViews;
    },
    setToken(state, token) {
        state.token = token;
    },
    setIsLoad(state, isActive) {
        state.isLoad = isActive;
    },
    addMyView(state, view) {
        if(!view) return;
        state.myViews.push(view);
        localStorage.setItem('views', JSON.stringify(state.myViews));
    },
    removeMyView(state, view) {
        const index = state.myViews.findIndex((item) => item === view);
        if (index !== -1) {
            state.myViews.splice(index, 1);
            localStorage.setItem('views', JSON.stringify(state.myViews));
        }
    },
    updateLocalStorage(state){
        localStorage.setItem('views', JSON.stringify(state.myViews));
    }
};
