import store from "../../stores";
export default function (promise, callback) {
    console.log(promise);
    void store.dispatch('visibility_error', promise.message)
    return {
        isError: true,
        message: promise.message,
        // status: promise.response.status
    };
}