<template>

    <div class="row">
        <search-result-item v-for="item in items" :key="item.imdbID" :item="item"/>
    </div>
</template>

<script>
import SearchResultItem from "./SearchResultItem.vue";

export default {
    name: "SearchResultList",
    components: {SearchResultItem},
    props: {
        items: Array,
    },
    methods:{
        showItem(){
            console.log(this.items);
        }
    }
}
</script>

<style scoped>

</style>