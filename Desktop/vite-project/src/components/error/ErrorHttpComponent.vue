<template>
    <div  class="bg-danger text-center text-white error-block w-100" v-on:click="hidden">
        {{STATE_MESSAGE}}
    </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";


export default {
    name: "ErrorHttpComponent",
    computed:{
        ...mapGetters(['STATE_MESSAGE'])
    },
    methods:{
        ...mapActions(['hidden_error']),
        hidden(){
            this.hidden_error();
        }
    },
}
</script>

<style scoped>
.error-block{
    cursor: pointer;
}
</style>
