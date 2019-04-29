<template>
    <div id="app">
        <el-row>
            <el-col :span="6">
                <el-form :model="queryForm" ref="queryForm" label-width="80px" :inline="true" style="width: 100%">
                    <el-form-item label="用户名">
                        <el-input v-model="queryForm.username" clearable></el-input>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <el-row>
            <el-button @click="searchForm('queryForm')">查询</el-button>
            <el-button @click="resetForm('queryForm')">重置</el-button>
            <el-button @click="toAdd">添加</el-button>
        </el-row>
        <el-row>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="username" label="用户名" width="120"/>
                <el-table-column prop="name" label="姓名" width="120"/>
                <el-table-column label="操作" width="100">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                        <el-button type="text" size="small">编辑</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="queryForm.page"
                    :page-sizes="[10, 20, 50]"
                    :page-size="queryForm.size"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="queryForm.total">
            </el-pagination>
        </el-row>

        <el-dialog :visible.sync="dialog.show">
            <div slot="title" class="header-title">
                <span> {{ dialog.title }}</span>
            </div>
            <el-form :model="dialog.form" label-width="80px" :rules="dialog.rules" ref="dialog.form"
                     style="width: 50%;margin-left: 25%;margin-right: 25%;">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="dialog.form.username" autocomplete="off" show-password></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="dialog.form.name" autocomplete="off" show-password></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="dialog.form.password" autocomplete="off" show-password></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialog.show = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>


    </div>
</template>

<script lang="ts">
    import Vue from 'vue'

    export default Vue.extend({

        data() {
            return {
                queryForm: {
                    username: null,
                    page: 0,
                    size: 10,
                    total: 0,
                },
                tableData: [],
                dialog: {
                    title: "添加",
                    show: false,
                    form: {
                        username: "",
                        name: "",
                        password: "",
                    },
                    rules: {
                        username: [
                            {required: true, message: '请输入用户名', trigger: 'blur'},
                            {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}],
                        name: [
                            {required: true, message: '请输入密码', trigger: 'blur'}],
                        password: [
                            {required: true, message: '请输入密码', trigger: 'blur'}],
                    }
                }
            }
        },
        created() {
            this.searchForm("queryForm");
        },
        methods: {
            handleSizeChange(val: number) {
                this.queryForm.size = val;
                this.searchForm('queryForm');
            },
            handleCurrentChange(val: number) {
                this.queryForm.page = val;
                this.searchForm('queryForm');
            },
            searchForm(formName: string) {
                let t = this;
                Vue.axios.post("/user/list", t.$data[formName]).then(function (res) {
                    if (res.data.code == 200) {
                        t.tableData = res.data.entity.content;
                        t.queryForm.page = res.data.entity.pageable.pageNumber
                        t.queryForm.size = res.data.entity.pageable.pageSize
                        t.queryForm.total = res.data.entity.totalElements
                    }
                })
            },
            resetForm(formName: string) {
                this.$refs[formName].resetFields();
            },
            toAdd() {
                this.dialog.show = true;
                this.dialog.title = "添加";
                this.dialog.form = {
                    username: "",
                    name: "",
                    password: "",
                }
                this.resetForm('dialog.form')
            },
            toUpdate(username: string) {
                let t = this;
                Vue.axios.post("/user/findUserByUsername/"+username).then(function (res) {
                    if (res.data.code == 200) {
                        t.resetForm('dialog.form')
                        t.dialog.show = true;
                        t.dialog.title = "修改";
                        t.dialog.form = {
                            username: "",
                            name: "",
                            password: "",
                        }

                    }
                })
            },
            save() {

            }
        },


    });

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
