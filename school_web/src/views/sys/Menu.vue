<template>
    <div id="app">
        <el-row>
            <el-breadcrumb separator="/" style="height: 40px;">
                <el-breadcrumb-item>系统管理</el-breadcrumb-item>
                <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
        <el-row>
            <el-button @click="flushPage">刷新</el-button>
            <el-button @click="toAdd">添加</el-button>
        </el-row>
        <el-row>
            <el-table :data="tableData" border row-key="id" lazy :load="loadChildren">
                <el-table-column prop="name" label="名称"></el-table-column>
                <el-table-column prop="url" label="链接"></el-table-column>
                <el-table-column prop="permission" label="权限"></el-table-column>
                <el-table-column prop="type" label="类型"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button @click="toAddChild(scope.$index, scope.row)" type="text" size="small">添加</el-button>
                        <el-button @click="toUpdate(scope.$index, scope.row)" type="text" size="small">编辑</el-button>
                        <el-button @click.prevent="toDelete(scope.$index, scope.row)" type="text" size="small">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-row>
        <el-dialog :visible.sync="dialog.show">
            <div slot="title" class="header-title">
                <span> {{ dialog.title }}</span>
            </div>
            <el-form :model="dialog.form" label-width="80px" :rules="dialog.rules" ref="dialog.form"
                     style="width: 50%;margin-left: 25%;margin-right: 25%;">
                <input type="hidden" v-model="dialog.form.id"/>
                <el-form-item label="菜单名称" prop="name">
                    <el-input v-model="dialog.form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="链接" prop="url">
                    <el-input v-model="dialog.form.url" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="权限" prop="permission">
                    <el-input v-model="dialog.form.permission" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="类型" prop="type">
                    <el-input v-model="dialog.form.type" autocomplete="off"></el-input>
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
                tableData: [],
                dialog: {
                    saveType: 0,
                    title: "添加",
                    show: false,
                    form: {
                        id: "",
                        name: "",
                        url: "",
                        permission: "",
                        type: "",
                        "menu.id": null,
                    },
                    rules: {
                        name: [{required: true, message: '请输入菜单名', trigger: 'blur'}],
                    }
                }
            }
        },
        created() {
            this.flushPage()
        },
        methods: {
            flushPage() {
                this.search({});
            },
            search(data: any) {
                let t = this;
                Vue.axios.post("/menu/list", data).then(function (res) {
                    if (res.data.code == 200) {
                        t.tableData = res.data.entity;
                    }
                })
            },
            loadChildren(tree: any, treeNode: any, resolve: any) {
                Vue.axios.post("/menu/loadChildren/" + treeNode.rowKey, {}).then(res => {
                    if (res.data.code == 200) {
                        resolve(res.data.entity)
                    }
                });
            },
            toAdd() {
                this.dialog.show = true;
                this.dialog.title = "添加";
                this.dialog.saveType = 1;
                this.dialog.form = {
                    id: "",
                    name: "",
                    url: "",
                    permission: "",
                    type: "",
                    "menu.id": null,
                }
            },
            toAddChild(index: any, row: any) {
                debugger
                this.dialog.show = true;
                this.dialog.title = "添加";
                this.dialog.saveType = 1;
                this.dialog.form = {
                    id: "",
                    name: "",
                    url: "",
                    permission: "",
                    type: "",
                    "menu.id": row.id,
                }
            },
            toUpdate(index: number, row: any) {
                this.dialog.show = true;
                this.dialog.saveType = 2;
                this.dialog.title = "修改";
                this.showDialogForm(row.id);
            },
            toDelete(index: number, row: any) {
                let t = this;
                Vue.axios.post("/menu/deleteById/" + row.id).then(function (res) {
                    let data = res.data;
                    if (data.code == 200) {
                        t.$message({
                            message: '保存成功!',
                            type: 'success',
                            showClose: true,
                        });
                        t.flushPage();
                    }
                })
            },
            showDialogForm(id: Number) {
                let t = this;
                Vue.axios.post("/menu/findById/" + id).then(function (res) {
                    let data = res.data;
                    if (data.code == 200) {
                        if (data.entity.menu != null) {
                            t.dialog.form = {
                                id: data.entity.id,
                                name: data.entity.name,
                                url: data.entity.url,
                                permission: data.entity.permission,
                                type: data.entity.type,
                                "menu.id": data.entity.menu.id,
                            }
                        } else {
                            t.dialog.form = {
                                id: data.entity.id,
                                name: data.entity.name,
                                url: data.entity.url,
                                permission: data.entity.permission,
                                type: data.entity.type,
                            }
                        }

                    }
                })
            },
            validate() {
                this.$refs['dialog.form'].validate((valid: boolean) => {
                    if (valid) {
                        this.save()
                    } else {
                        return false;
                    }
                });
            },
            save() {
                let t = this;
                let url;
                if (this.dialog.saveType === 1) {
                    url = "/menu/add";
                } else if (t.dialog.saveType === 2) {
                    url = "/menu/update";
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
                        t.flushPage();
                    }
                });
            },
        },
    });

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .el-table {
        width: 100%;
    }

    .el-form {
        width: 100%;
        margin-left: 0;
        margin-right: 0;
    }
</style>
