<template>
  <div id="app">
    <el-row>
      <el-breadcrumb separator="/" style="height: 40px;">
        <el-breadcrumb-item>学生信息</el-breadcrumb-item>
        <el-breadcrumb-item>我的课程</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <br>
    <br>
    <br>
    <el-row>
      <el-table :data="tableData" border style="width: 100%" stripe>
        <el-table-column prop="courseName" label="课程名称"/>
        <el-table-column prop="teacherName" label="课程老师"/>
        <el-table-column prop="score" label="课程得分"/>
      </el-table>
    </el-row>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  data() {
    return {
      tableData: []
    };
  },
  created() {
    this.searchForm();
  },
  methods: {
    searchForm() {
      let t = this;
      Vue.axios.post("/student/myCourse").then(function(res) {
        if (res.data.code == 200) {
          t.tableData = res.data.entity;
        }
      });
    }
  }
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
