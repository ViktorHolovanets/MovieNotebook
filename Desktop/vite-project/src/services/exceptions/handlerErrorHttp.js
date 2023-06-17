import store from "../../stores";
export default function (promise, callback) {
    hiddenError();
    void store.dispatch('visibility_error', promise.message)
    return {
        isError: true,
        message: promise.message,
    };
}
function hiddenError() {
    setTimeout(() => {
        store.dispatch( 'hidden_error');
    }, 5000);
}