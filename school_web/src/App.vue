<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<script>
    import Vue from "vue";

    export default Vue.extend({
        created() {
            let t = this;
            //验证是否有token
            let auth = sessionStorage.getItem("Authorization");
            if (!auth) {
                t.$router.replace("/login")
            }
            //验证session是否到期
            Vue.axios.get("/").then(function (res) {
                if (res.data.code == 200) {
                    t.$message({
                        message: '登陆成功!',
                        type: 'success',
                        showClose: true,
                    });
                    t.$router.replace("/home")
                } else {
                    sessionStorage.clear();
                    t.$router.replace("/login")
                }
            })
        }
    })
</script>

<style scoped>

</style>