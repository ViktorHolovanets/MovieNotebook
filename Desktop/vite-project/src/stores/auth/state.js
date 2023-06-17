export default {
    myViews: JSON.parse(localStorage.getItem('views')) || [],
    token: localStorage.getItem('token'),
    isLoad: false,
};
