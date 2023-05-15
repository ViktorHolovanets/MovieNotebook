import { createApp } from 'vue'
import './style.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'
import store from './stores/index'
import App from './App.vue'
import router from './router'

const app = createApp(App);

app.use(router);
app.use(store);

app.mount("#app");

