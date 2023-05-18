<script setup>
import HelloWorld from './components/HelloWorld.vue'
import {useStore} from 'vuex';
import { computed } from 'vue';
import Login from "./components/auth/login.vue";
import ErrorHttpComponent from "./components/error/ErrorHttpComponent.vue";
import SearchResultItem from "./components/search/result/SearchResultItem.vue";
import SearchComponent from "./components/search/result/SearchComponent.vue";
import MainMenu from "./components/menu/mainMenu.vue";
import FilterComponent from "./components/search/filter/filterComponent.vue";

const store = useStore();
const token = computed(() => store.getters.getToken);
const isOpenFilter = computed(() => store.getters.getFilter);

function isEmptyOrNull(str) {
    return str === null || str.trim() === '';
}
</script>

<template>
    <error-http-component v-if="store.getters.STATE_ERROR"/>
    <login v-if="isEmptyOrNull(token)"/>
    <div v-else>
        <search-component/>
        <main-menu/>
        <filter-component v-if="isOpenFilter"/>
    </div>

</template>

<style scoped>

</style>
