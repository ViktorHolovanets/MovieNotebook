<template>
    <div class="h1 text-center heading">My views</div>
    <my-view-item v-if="isEmpty()" v-for="item in present()" :key="item.imdbID" :item="item"/>
    <div v-else>
        <div class="container">
            <div class="alert alert-danger" role="alert">
      <span class="icon">
        <i class="bi bi-exclamation-triangle-fill"></i>
      </span>
                Колекція не знайдена.
            </div>
        </div>
    </div>
</template>

<script>


import MyViewItem from "./myViewItem.vue";
import {mapGetters} from "vuex";

export default {
    name: "myViewComponent",
    components: {MyViewItem},
    computed:{
        ...mapGetters(['getMyViews'])
    },
    methods:{
        isEmpty(){
            if(this.getMyViews){
                if(this.getMyViews.length>0)
                    return true;
                return false
            }
            return false
        },
        present(){
            const params=this.$route.params.type;
            if(params==='wants'){
                const presentResult = this.getMyViews.filter(item => !item.isView);
                return presentResult??[]
            }
            return this.getMyViews.filter(item => item.isView)??[];
        }
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

.container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.alert {
    display: flex;
    align-items: center;
    padding: 20px;
    background-color: #f8d7da;
    color: #721c24;
    border: 1px solid #f5c6cb;
    border-radius: 4px;
    font-family: 'Roboto', sans-serif;
    font-size: 18px;
    animation: shake 0.5s;
    animation-iteration-count: infinite;
}

.icon {
    margin-right: 10px;
    font-size: 24px;
}

.alert-danger {
    animation-timing-function: cubic-bezier(0.36, 0.07, 0.19, 0.97);
}

@keyframes shake {
    0% {
        transform: translateX(0);
    }
    25% {
        transform: translateX(-5px);
    }
    50% {
        transform: translateX(5px);
    }
    75% {
        transform: translateX(-5px);
    }
    100% {
        transform: translateX(0);
    }
}
</style>