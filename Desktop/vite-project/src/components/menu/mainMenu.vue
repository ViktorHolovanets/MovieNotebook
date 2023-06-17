<template>
    <div class="menu" onclick="this.classList.toggle('open')">
        <i class="fas fa-caret-down text-white title-menu">Menu</i>
        <menu-item-component :bottom="'-70px'"
                             :right="'15px'"
                             :background="'url(https://unpkg.com/feather-icons@4.29.0/dist/icons/search.svg)'"
                             :onClick="showFilter"
                             title="SEARCH"
        />
        <menu-item-component :bottom="'-14px'"
                             :right="'100px'"
                             :background="'url(https://unpkg.com/feather-icons@4.29.0/dist/icons/video.svg)'"
                             :onClick="goHome"
                             title="My views"
        />
        <menu-item-component :bottom="'50px'"
                             :right="'15px'"
                             :background="'url(https://unpkg.com/feather-icons@4.29.0/dist/icons/heart.svg)'"
                             :onClick="goHomeWants"
                             title="Want views"
        />
        <div class="position-absolute button-exit bg-danger rounded rounded-circle button" @click="exit" title="EXIT">
            <i class="fas fa-arrow-right-from-bracket text-white"></i>
        </div>
    </div>
</template>

<script>
import '@fortawesome/fontawesome-free/css/all.css';
import MenuItemComponent from "./menuItemComponent.vue";
import {mapActions} from "vuex";
import router from "../../router";

export default {
    name: "mainMenu",
    components: {MenuItemComponent},
    methods: {
        ...mapActions(['updateIsFilter','updateToken']),
        showFilter() {
            this.updateIsFilter(true)
        },
        async exit() {
            await this.updateToken('');
            localStorage.clear();
            await this.goHome();
        },
        async goHome() {
            await router.push({ name: 'home', params: { type: 'views' } });
        },
        async goHomeWants() {
            await router.push({ name: 'home', params: { type: 'wants' } });
        }
    }
}
</script>

<style scoped lang="scss">

.menu,
.menu:before,
.menu:after {
  border-radius: 10px;
    padding: 5px;
}

.menu {
  position: fixed;
  top: 75px;
  right: 150px;
  cursor: pointer;

  &::before {
    content: '';
    background: linear-gradient(45deg, #db70ee, #ff7300, #fffb00, #48ff00, #00ffd5, #002bff, #7a00ff, #ff00c8, #ff0000);
    position: absolute;
    top: -2px;
    left: -2px;
    background-size: 400%;
    z-index: -1;
    filter: blur(5px);
    width: calc(100% + 4px);
    height: calc(100% + 4px);
    animation: glowing 20s linear infinite;
    transition: opacity .3s ease-in-out;
    opacity: 1;
  }

  &::after {
    z-index: -1;
    content: '';
    position: absolute;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.7);
    left: 0;
    top: 0;
  }

  &.open {
    .button {
      opacity: 1;
      pointer-events: auto;
    }
  }
}

@keyframes glowing {
  0% {
    background-position: 0 0;
  }
  50% {
    background-position: 400% 0;
  }
  100% {
    background-position: 0 0;
  }
}

.title-menu {
  font-size: 1.5rem;

}
.button-exit{
    bottom: -15px;
    right: -63px;
    width: 60px;
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    opacity: 0;
    pointer-events: none;
    box-shadow: inherit;
    transition: 0.2s cubic-bezier(0.18, 0.89, 0.32, 1.28), 0.2s ease opacity, 0.2s cubic-bezier(.08, .82, .17, 1) transform;
}
</style>