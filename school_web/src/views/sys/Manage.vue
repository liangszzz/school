<template>
    <div>
        <!--查询card-->
        <el-card>
            <el-form :model="form" label-width="80px">
                <el-form-item label="用户名">
                    <el-col :span="6">
                        <el-input v-model="form.username" clearable size="small"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="search"><i class="el-icon-search"></i>查询</el-button>
                    <el-button type="primary"><i class="el-icon-plus el-icon-left"></i>添加</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-table :data="tableData">
            <el-table-column fixed prop="date" label="日期" width="150">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="province" label="省份" width="120">
            </el-table-column>
            <el-table-column prop="city" label="市区" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址" width="300">
            </el-table-column>
            <el-table-column prop="zip" label="邮编" width="120">
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="120">
                <template slot-scope="scope">
                    <el-button @click.native.prevent="deleteRow(scope.$index, tableData)" type="text" size="small">
                        移除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';

    @Component
    export default class Manage extends Vue {

        form = {
            username:  ' '
        };

        tableData = [{
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
        }];

        created(): void {
            console.log( '##created ');
        }


        search(): void {
            console.log( '##search ');
        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
