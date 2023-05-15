<script setup>
import HelloWorld from './components/HelloWorld.vue'
import {useStore} from 'vuex';
import { computed } from 'vue';
import Login from "./components/auth/login.vue";
import ErrorHttpComponent from "./components/error/ErrorHttpComponent.vue";

const store = useStore();
const con = computed(() => store.getters.getToken);

function isEmptyOrNull(str) {
    return str === null || str.trim() === '';
}
</script>

<template>
    <error-http-component v-if="store.getters.STATE_ERROR"/>
    <login v-if="isEmptyOrNull(con)"/>
    <div v-else>
        <div>
            <a href="https://vitejs.dev" target="_blank">
                <img src="" className="logo" alt="Vite logo"/>
            </a>
            <a href="https://vuejs.org/" target="_blank">
                <img src="./assets/vue.svg" className="logo vue" alt="Vue logo"/>
            </a>
        </div>
        <HelloWorld msg="Vite + Vue"/>
    </div>
</template>

<style scoped>

</style>
