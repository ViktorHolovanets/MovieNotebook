<template>
    <div class="modal-filter">
        <div class="content-filter bg-black  p-5 rounded text-bg-danger col-12 col-sm-12 col-md-6 col-lg-4">
            <div class="h3 text-center m-4">Filter</div>
            <hr/>
            <checkbox-component :is-checked="isMovie" @update:isChecked="isMovie = $event" :label="'Movie'"/>
            <checkbox-component :is-checked="isSerial" @update:isChecked="isSerial = $event" :label="'Serial'"/>
            <select class="w-75 d-block m-3">
                <option value="All">All</option>
                <option v-for="year in getYearsRange(1965, getCurrentYear())" :value="year">{{ year }}</option>
            </select>
            <button class="btn btn-outline-success col-12 col-sm-12 col-md-6 col-lg-4 m-2" v-on:click="search">Search</button>
            <button class="btn btn-outline-danger col-12 col-sm-12 col-md-6 col-lg-4 m-2" v-on:click="exit">Exit</button>
        </div>
    </div>
</template>

<script>
import CheckboxComponent from "../../elements/checkboxComponent.vue";
import {mapActions} from "vuex";

export default {
    name: "filterComponent",
    components: {
        CheckboxComponent,
    },
    data() {
        return {
            isMovie: false,
            isSerial: false,
        };
    },
    computed: {
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
        ...mapActions(['updateIsFilter']),
        search() {
            console.log(this.isMovie);
            console.log(this.isSerial);
        },
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
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: rgba(139, 112, 219, 0.7);
    z-index: 1000;
    display: flex;
    justify-content: space-around;
    align-items: center;
}
</style>