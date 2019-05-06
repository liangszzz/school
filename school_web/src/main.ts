import Vue from 'vue';
import './plugins/element.js'
import App from './App.vue';
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from './router';

Vue.use(VueAxios, axios)
Vue.config.productionTip = false;

const Qs = require('qs');
Vue.axios.defaults.baseURL = 'http://127.0.0.1:8000/';
Vue.axios.defaults.headers = {
    'content-type': 'application/x-www-form-urlencoded'
}

Vue.axios.defaults.transformRequest = [function (data, headers) {
    if (headers['content-type'] == 'application/x-www-form-urlencoded') {
        return Qs.stringify(data, {arrayFormat: 'brackets'})
    } else if (headers['content-type'] == 'application/json') {
        return JSON.stringify(data);
    }
    return data;
},]

Vue.axios.interceptors.request.use(function (config) {
    let auth = sessionStorage.getItem("Authorization");
    if (auth) {
        config.headers['Authorization'] = auth
    }
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

// Add a response interceptor
axios.interceptors.response.use(function (response) {
    // Do something with response data
    return response;
}, function (error) {
    // Do something with response error
    return Promise.reject(error);
});


// Vue.prototype.checkPermission = function (permission){
//     let permissions = sessionStorage.getItem("permissions");
//     if (permissions.indexOf(permission) >= 0) {
//         return true;
//     }
//     return false;
// }

new Vue({
    router,
    render: (h) => h(App),
}).$mount('#app');
