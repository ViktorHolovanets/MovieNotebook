import { createRouter, createWebHistory } from 'vue-router'
import HelloWorld from "../components/HelloWorld.vue";




const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HelloWorld,
        },
        // {
        //     path: '/player',
        //     name: 'player',
        //     component: PlayerComponents,
        // },
        // {
        //     path: '/SignIn',
        //     name: 'login',
        //     component: SignInViews
        // },
        // {
        //     path: '/login',
        //     name: 'login',
        //     component: LogRegComponent,
        // },
        // {
        //     path: '/profile/:id?',
        //     name: 'profile',
        //     component: ProfileComponent
        // },
        // {
        //     path: '/SignUp',
        //     name: 'register',
        //     component: SignUpViews
        // }
    ]
})

export default router