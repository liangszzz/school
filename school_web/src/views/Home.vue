<template>
    <div id="app">
        <el-row class="home-top">
            <el-col :span="24">
                <el-tag>{{userType}}:</el-tag>
                <el-tag>{{username}}</el-tag>
                <el-button size="mini" @click="updatePwd">密码</el-button>
                <el-button size="mini" @click="logout">注销</el-button>
            </el-col>
        </el-row>
        <el-row class="home-body">
            <el-col :span="4">
                <el-menu
                        default-active="1"
                        unique-opened="true"
                        menu-trigger="click"
                        router="true"
                        class="el-menu-vertical-demo"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffd04b">
                    <el-submenu index="1">
                        <template slot="title">
                            <span>系统管理</span>
                        </template>
                        <el-menu-item index="/manage">
                            管理员管理
                        </el-menu-item>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title">
                            <span>学校管理</span>
                        </template>
                        <el-menu-item>学年管理</el-menu-item>
                        <el-menu-item>教室管理</el-menu-item>
                        <el-menu-item>班级管理</el-menu-item>
                        <el-menu-item>教师管理</el-menu-item>
                        <el-menu-item>课程管理</el-menu-item>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title">
                            <span>学生管理</span>
                        </template>
                        <el-menu-item>学生入学</el-menu-item>
                        <el-menu-item>学生调班</el-menu-item>
                        <el-menu-item>学生退学</el-menu-item>
                    </el-submenu>
                    <el-submenu index="4">
                        <template slot="title">
                            <span>学生信息</span>
                        </template>
                        <el-menu-item>学生选课</el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-col>
            <el-col :span="20">
                <router-view/>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import Vue from 'vue'

    export default Vue.extend({
        data() {
            return {
                username: "",
                userType: "",
            }
        },
        methods: {
            created() {
                //获取用户信息
            },
            updatePwd() {
                console.log("##1");
            },
            logout() {
                console.log("##2");
            }
        }
    });


</script>

<style>
    #app {
        text-align: center;
    }

    .home-top {
        height: 20%;
        margin-bottom: 5px;
        float: right;
    }

    .home-body {
        height: 80%;
    }
</style>
