import { createRouter, createWebHistory } from 'vue-router'
import HelloWorld from "../components/HelloWorld.vue";
import SearchComponent from "../components/search/result/SearchComponent.vue";
import MyViewItem from "../components/myViews/myViewItem.vue";
import MyViewComponent from "../components/myViews/myViewComponent.vue";




const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/:type?',
            name: 'home',
            component: MyViewComponent,
        },
        {
            path: '/find',
            name: 'find',
            component: SearchComponent,
            props: true
        }
    ]
})

export default router