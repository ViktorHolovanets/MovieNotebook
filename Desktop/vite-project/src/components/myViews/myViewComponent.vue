<template>
    <div class="h1 text-center heading">My views</div>
    <my-view-item v-if="getMyViews.length>0" v-for="item in getMyViews" :key="item.imdbID" :item="item"/>
    <div v-else>Not Film</div>
</template>

<script>


import MyViewItem from "./myViewItem.vue";
import {mapGetters} from "vuex";

export default {
    name: "myViewComponent",
    components: {MyViewItem},
    computed:{
        ...mapGetters(['getMyViews'])
    }
}
</script>

<style scoped>
.heading {
    font-size: 3rem;
    color: white;
    text-transform: uppercase;
    letter-spacing: 0.4rem;
    text-shadow: 0 0 19px rgb(20, 73, 247), 0 0 20px rgba(255, 255, 255, 0.568), 0 0 30px #1a0e34, 0 0 40px #721faad9, 0 0 50px #2c26729f, 0 0 60px #180e5c5c, 0 0 70px #101d3548;
    border-radius: 70%;
    padding: 0.5rem;
    position: relative;
}
</style>