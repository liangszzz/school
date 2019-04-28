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
            <el-button>重置</el-button>
            <el-button>添加</el-button>
        </el-row>
        <el-row>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="username" label="用户名" width="120"/>
                <el-table-column label="操作" width="100" fixed="right">
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
                Vue.axios.post("/manage/list", t.queryForm).then(function (res) {
                    if (res.data.code == 200) {
                        t.tableData = res.data.page.content;
                    }
                })
            },
            resetForm(formName: string) {

            },
            toAdd() {

            },
        },


    });

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
