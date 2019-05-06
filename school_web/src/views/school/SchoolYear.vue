<template>
    <div id="app">
        <el-row>
            <el-breadcrumb separator="/" style="height: 40px;">
                <el-breadcrumb-item>校园管理</el-breadcrumb-item>
                <el-breadcrumb-item>学年管理</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
        <el-row>
            <el-form :model="queryForm" ref="queryForm" label-width="80px">
                <el-col :span="6">
                    <el-form-item label="名称">
                        <el-input v-model="queryForm.name" clearable></el-input>
                    </el-form-item>
                </el-col>
            </el-form>
        </el-row>
        <el-row>
            <el-button @click="searchForm('queryForm')">查询</el-button>
            <el-button @click="toAdd">添加</el-button>
        </el-row>
        <el-row>
            <el-table :data="tableData" border style="width: 100%" stripe>
                <el-table-column prop="name" label="学年名称"/>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button @click="toShow(scope.$index, scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="toUpdate(scope.$index, scope.row)" type="text" size="small">编辑</el-button>
                        <el-button @click.prevent="toDelete(scope.$index, scope.row)" type="text" size="small">删除
                        </el-button>
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
                <input v-model="dialog.form.id" type="hidden"></input>
                <el-form-item label="学年名称" prop="name">
                    <el-input v-model="dialog.form.name" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialog.show = false">取 消</el-button>
                <el-button type="primary" @click="validate">确 定</el-button>
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
                    name: null,
                    page: 0,
                    size: 10,
                    total: 0,
                },
                tableData: [],
                dialog: {
                    saveType: 0,
                    title: "添加",
                    show: false,
                    form: {
                        id: "",
                        name: "",
                    },
                    rules: {
                        name: [
                            {required: true, message: '请输入名称', trigger: 'blur'}],
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
                Vue.axios.post("/year/list", t.$data[formName]).then(function (res) {
                    if (res.data.code == 200) {
                        t.tableData = res.data.entity.content;
                        t.queryForm.page = res.data.entity.pageable.pageNumber
                        t.queryForm.size = res.data.entity.pageable.pageSize
                        t.queryForm.total = res.data.entity.totalElements
                    }
                })
            },
            toAdd() {
                this.dialog.show = true;
                this.dialog.title = "添加";
                this.dialog.saveType = 1;
                this.dialog.form = {
                    id: "",
                    name: "",
                }
            },
            toShow(index: number, row: any) {
                this.dialog.show = true;
                this.dialog.saveType = 0;
                this.dialog.title = "查看";
                this.showDialogForm(row.id)
            },
            toUpdate(index: number, row: any) {
                this.dialog.show = true;
                this.dialog.saveType = 2;
                this.dialog.title = "修改";
                this.showDialogForm(row.id);
            },
            toDelete(index: number, row: any) {
                let t = this;
                Vue.axios.post("/year/deleteById/" + row.id).then(function (res) {
                    let data = res.data;
                    if (data.code == 200) {
                        t.$message({
                            message: '保存成功!',
                            type: 'success',
                            showClose: true,
                        });
                        t.searchForm('queryForm')
                    }
                })
            },
            showDialogForm(id: Number) {
                let t = this;
                Vue.axios.post("/year/findById/" + id).then(function (res) {
                    let data = res.data;
                    if (data.code == 200) {
                        t.dialog.form = {
                            id: data.entity.id,
                            name: data.entity.name,
                        }
                    }
                })
            },
            validate() {
                this.$refs['dialog.form'].validate((valid:boolean) => {
                    if (valid) {
                        this.save()
                    } else {
                        return false;
                    }
                });
            },
            save() {
                let t = this;
                let url
                if (this.dialog.saveType === 1) {
                    url = "/year/add";
                } else if (t.dialog.saveType === 2) {
                    url = "/year/update";
                } else {
                    t.dialog.show = false;
                    return;
                }
                Vue.axios.post(url, this.dialog.form).then(res => {
                    if (res.data.code == 200) {
                        t.$message({
                            message: '保存成功!',
                            type: 'success',
                            showClose: true,
                        });
                        t.dialog.show = false;
                        t.searchForm('queryForm')
                    }
                });

            }
        },


    });

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .el-form {
        width: 100%;
        margin-left: 0;
        margin-right: 0;
    }

</style>
