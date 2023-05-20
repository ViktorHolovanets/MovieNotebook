<template>
    <search-result-list :items="getSearchResult"/>
    <div class="d-flex justify-content-center">
        <button class="btn btn-success m-3">Prev</button>
        <button class="btn btn-primary m-3">Next</button>
    </div>
</template>

<script>
import SearchResultList from "./SearchResultList.vue";
import {mapGetters} from "vuex";

export default {
    name: "SearchComponent",
    components: {SearchResultList},
    computed: {
        ...mapGetters(['getSearchResult'])
    },
    mounted() {
        console.log(this.getSearchResult)
    }
}
</script>

<style scoped>

</style>