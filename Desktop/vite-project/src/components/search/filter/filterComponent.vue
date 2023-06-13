<template>
    <div class="modal-filter">
        <div class="content-filter bg-black  p-5 rounded text-bg-danger col-12 col-sm-12 col-md-6 col-lg-4">
            <div class="h3 text-center m-4">Filter</div>
            <hr/>
            <input type="text" class="form-control input" placeholder="Enter title..." v-model="title"/>
            <checkbox-component :is-checked="isMovie" @update:isChecked="isMovie = $event" :label="'Movie'"/>
            <checkbox-component :is-checked="isSerial" @update:isChecked="isSerial = $event" :label="'Serial'"/>
            <select class="d-block input form-control" v-model="year">
                <option value="null">All</option>
                <option v-for="year in getYearsRange(1965, getCurrentYear())" :value="year">{{ year }}</option>
            </select>
            <button class="btn btn-outline-success col-12 col-sm-12 col-md-6 col-lg-4 m-2" v-on:click="search">Search
            </button>
            <button class="btn btn-outline-danger col-12 col-sm-12 col-md-6 col-lg-4 m-2" v-on:click="exit">Exit
            </button>
        </div>
    </div>
</template>

<script>
import {_search} from "../../../services/http/httpRequestToServer";
import CheckboxComponent from "../../elements/checkboxComponent.vue";
import {mapActions, mapGetters} from "vuex";

export default {
    name: "filterComponent",
    components: {
        CheckboxComponent,
    },
    data() {
        return {
            isMovie: false,
            isSerial: false,
            year: null,
            title: '',
        };
    },
    computed: {
        ...mapGetters(['getParams']),
        updateIsMovie: {
            get() {
                return this.isMovie;
            },
            set(value) {
                this.isMovie = value;
            },
        },
        updateIsSerial: {
            get() {
                return this.isSerial;
            },
            set(value) {
                this.isSerial = value;
            },
        },
    },
    methods: {
        ...mapActions(['updateIsFilter', 'updateSearchResult', 'updateParamTitle', 'updateParamType', 'updateParamPage', 'updateParamYear', 'updateIsFilter']),
        async search() {
            const type = this.isMovie && !this.isSerial ? "movie" : this.isSerial && !this.isMovie ? "series" : null;
            this.updateParamType(type);
            this.updateParamTitle(this.title)
            this.updateParamPage(1)
            this.updateParamYear(this.year)
            const data = await _search(this.getParams);
            this.updateIsFilter(false);
            if (data.value) {
                this.updateSearchResult(data.value);
            }
            this.$router.push({name: 'find'});

        }
        ,
        exit() {
            this.updateIsFilter(false);
        },
        getYearsRange(startYear, endYear) {
            const years = [];
            for (let year = startYear; year <= endYear; year++) {
                years.push(year);
            }
            return years.reverse();
        },
        getCurrentYear() {
            const date = new Date();
            return date.getFullYear();
        }
    },
};
</script>


<style scoped>
.modal-filter {
    left: 0;
    top: 0;
    position: fixed;
    width: 100%;
    height: 100%;
    background-color: rgba(164, 168, 166, 0.7);
    z-index: 1000;
    display: flex;
    justify-content: space-around;
    align-items: center;
}
.input {
    background-color: #212121;
    padding: 10px;
    color:white;
    border: 2px solid white;
    border-radius: 5px;
    /* box-shadow: 3px 3px 2px rgb(249, 255, 85); */
}

.input:focus {
    color: rgb(0, 255, 255);
    background-color: #212121;
    outline-color: rgb(0, 255, 255);
    box-shadow: -3px -3px 15px rgb(0, 255, 255);
    transition: .1s;
    transition-property: box-shadow;
}
</style>