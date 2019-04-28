import Vue from 'vue';
import Router from 'vue-router';
import Login from './views/Login.vue';
import Home from './views/Home.vue';
import User from './views/sys/User.vue'

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/login',
            name: 'login',
            component: Login,
        },
        {
            path: '/about',
            name: 'about',
            component: () => import(/* webpackChunkName: "about" */ './views/About.vue'),
        },
        {
            path: '/home',
            name: 'home',
            component: Home,
            children: [
                {
                    path: '/user',
                    name: 'user',
                    component: User,
                },
            ]
        },

    ],
});
