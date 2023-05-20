<template>
    <div>
        <router-view></router-view>
        <main-menu/>
        <filter-component v-if="getFilter"/>
    </div>
</template>

<script>
import FilterComponent from "../components/search/filter/filterComponent.vue";
import MainMenu from "../components/menu/mainMenu.vue";
import {mapGetters} from "vuex";

export default {
    name: "mainComponent",
    components: {MainMenu, FilterComponent},
    computed:{
        ...mapGetters(['getFilter'])
    }
}
</script>

<style scoped>

</style>