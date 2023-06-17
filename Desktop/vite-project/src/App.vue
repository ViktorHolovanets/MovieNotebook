<script setup>

import {useStore} from 'vuex';
import {computed} from 'vue';
import Login from "./components/auth/login.vue";
import ErrorHttpComponent from "./components/error/ErrorHttpComponent.vue";
import MainComponent from "./views/mainComponent.vue";
import LoaderComponent from "./components/loader/loaderComponent.vue";

const store = useStore();
const token = computed(() => store.getters.getToken);
const isLoad = computed(() => store.getters.getIsLoad);

function isEmptyOrNull(str) {
    return str === null || str.trim() === '';
}
</script>

<template>
    <img class="myBody" src="./assets/images/hexagons.png"/>
    <div class="myBg">
        <loader-component v-if="isLoad"/>
        <error-http-component v-if="store.getters.STATE_ERROR" class="position-relative"/>
        <login v-if="isEmptyOrNull(token)"/>
        <div v-else>
            <main-component/>
        </div>
    </div>

</template>

<style scoped>
.myBody {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    z-index: -1; /* Встановлюємо менший z-index для зображення, щоб воно було позаду інших елементів */
}


.myBg {
    /* Встановлюємо більший z-index для контейнера, щоб він був попереду зображення */
    position: relative;
    z-index: 1;
}
</style>
