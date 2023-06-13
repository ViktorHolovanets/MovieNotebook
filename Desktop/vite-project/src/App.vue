<script setup>

import {useStore} from 'vuex';
import {computed} from 'vue';
import Login from "./components/auth/login.vue";
import ErrorHttpComponent from "./components/error/ErrorHttpComponent.vue";
import MainComponent from "./views/mainComponent.vue";

const store = useStore();
const token = computed(() => store.getters.getToken);

function isEmptyOrNull(str) {
    return str === null || str.trim() === '';
}
</script>

<template>
    <img class="myBody" src="./assets/images/hexagons.png"/>
    <error-http-component v-if="store.getters.STATE_ERROR" class="position-relative"/>
    <login v-if="isEmptyOrNull(token)"/>
    <div v-else>
        <main-component/>
    </div>
</template>

<style scoped>
.myBody {
    position: fixed;
    top:0;
    left: 0;
    width: 100%;


}
.myBodyHeight{
    height: 100%;
}
</style>
