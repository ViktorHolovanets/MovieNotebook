<template>
    <div class="z-index10">
        <search-result-list :items="getSearchResult||[]"/>
        <div class="d-flex justify-content-center navigation">
            <button v-if="page!=1" class="btn btn-success m-3" v-on:click="prev">Prev</button>
            <button class="btn btn-primary m-3" v-on:click="next">Next</button>
        </div>
    </div>
</template>

<script>
import SearchResultList from "./SearchResultList.vue";
import {mapGetters, mapActions} from "vuex";
import {_search} from "../../../services/http/httpRequestToServer";

export default {
    name: "SearchComponent",
    data() {
        return {
            page: 1
        }
    },
    components: {SearchResultList},
    computed: {
        ...mapGetters(['getSearchResult', 'getParams'])
    },
    methods: {
        ...mapActions(['updateParamPage', 'updateSearchResult']),
        prev() {
            this.page -= 1
            this.runNavigation()
        },
        next() {
            this.page += 1
            this.runNavigation()
        },
        async runNavigation() {
            await this.updateParamPage(this.page);
            const data = await _search(this.getParams);
            if (data.value) {
                this.updateSearchResult(data.value);
            } else {
                if (this.page != 1)
                    this.page -= 1
            }
        }
    }
}
</script>

<style scoped>
.z-index10{
    z-index: 10;
}
</style>