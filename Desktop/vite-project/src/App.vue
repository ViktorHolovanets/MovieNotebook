<script setup>
import HelloWorld from './components/HelloWorld.vue'
import {useStore} from 'vuex';
import { computed } from 'vue';
import Login from "./components/auth/login.vue";
import ErrorHttpComponent from "./components/error/ErrorHttpComponent.vue";
import SearchResultItem from "./components/search/SearchResultItem.vue";
import SearchComponent from "./components/search/SearchComponent.vue";

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
        <search-component/>
    </div>
</template>

<style scoped>

</style>
