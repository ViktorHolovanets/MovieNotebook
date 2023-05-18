<template>
    <div class="menu" onclick="this.classList.toggle('open')">
       <menu-item-component :bottom="'-50px'"
                            :right="'50px'"
                            :background="'url(https://unpkg.com/feather-icons@4.29.0/dist/icons/search.svg)'"
                            :onClick="showFilter"
        />
        <menu-item-component :bottom="'0'"
                             :right="'80px'"
                             :background="'url(https://unpkg.com/feather-icons@4.29.0/dist/icons/video.svg)'"
        />
        <menu-item-component :bottom="'50px'"
                             :right="'50px'"
                             :background="'url(https://unpkg.com/feather-icons@4.29.0/dist/icons/heart.svg)'"
        />
    </div>
</template>

<script>
import MenuItemComponent from "./menuItemComponent.vue";
import {mapActions} from "vuex";

export default {
    name: "mainMenu",
    components: {MenuItemComponent},
    methods:{
        ...mapActions(['updateIsFilter']),
        showFilter(){
            this.updateIsFilter(true)
        }
    }
}
</script>

<style scoped lang="scss">

.menu {
    position: fixed;
    bottom: 70px;
    right: 50px;
    padding: 30px;
    background: #faa003;
    border-radius: 100%;
    cursor: pointer;
    &::before, &::after {
        content: "";
        background: #f6f6f8;
        border-radius: 5px;
        width: 30px;
        height: 5px;
        position: absolute;
        left: 16px;
        top: 27px;
        transition: 0.2s ease;
        z-index: 1;
    }
    &::before {
        transform: rotate(0deg);
    }
    &::after {
        transform: rotate(-90deg);
    }
    &.open {
        opacity: .9;
        &::before {
            transform: rotate(45deg);
        }
        &::after {
            transform: rotate(-45deg);
        }
        .button {
            opacity: 1;
            pointer-events: auto;
        }
    }
}
</style>