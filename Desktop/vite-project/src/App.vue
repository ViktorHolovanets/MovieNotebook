<script setup>

import {useStore} from 'vuex';
import { computed } from 'vue';
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
    <error-http-component v-if="store.getters.STATE_ERROR"/>
    <login v-if="isEmptyOrNull(token)"/>
    <div v-else>
        <main-component/>
    </div>

</template>

<style scoped>

</style>
