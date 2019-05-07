import Vue from 'vue';
import Router from 'vue-router';
import Login from './views/Login.vue';
import Home from './views/Home.vue';
import User from './views/sys/User.vue'
import Role from './views/sys/Role.vue'
import Menu from "@/views/sys/Menu.vue";
import SchoolCourse from "@/views/school/SchoolCourse.vue";
import SchoolClass from "@/views/school/SchoolClass.vue";
import SchoolRoom from "@/views/school/SchoolRoom.vue";
import SchoolStudent from "@/views/school/SchoolStudent.vue";
import SchoolTeacher from "@/views/school/SchoolTeacher.vue";
import SchoolYear from "@/views/school/SchoolYear.vue";

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
                {
                    path: '/role',
                    name: 'role',
                    component: Role,
                },
                {
                    path: '/menu',
                    name: 'menu',
                    component: Menu,
                },
                {
                    path: '/class',
                    name: 'class',
                    component: SchoolClass,
                },
                {
                    path: '/course',
                    name: 'course',
                    component: SchoolCourse,
                },
                {
                    path: '/student',
                    name: 'student',
                    component: SchoolStudent,
                },
                {
                    path: '/teacher',
                    name: 'teacher',
                    component: SchoolTeacher,
                },
                {
                    path: '/year',
                    name: 'year',
                    component: SchoolYear,
                },
            ]
        },

    ],
});
