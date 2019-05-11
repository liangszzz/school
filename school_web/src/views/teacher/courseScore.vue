<template>
  <div id="app">
    <el-row>
      <el-breadcrumb separator="/" style="height: 40px;">
        <el-breadcrumb-item>老师信息</el-breadcrumb-item>
        <el-breadcrumb-item>课程评分</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <br>
    <br>
    <br>
    <el-row>
      <el-table :data="tableData" border style="width: 100%" stripe>
        <el-table-column prop="name" label="课程名称"/>
        <el-table-column prop="score" label="学分"/>
        <el-table-column prop="hour" label="课时"/>
        <el-table-column prop="schoolYear.name" label="学年"/>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="score(scope.$index, scope.row)" type="text" size="small">评分</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <el-dialog :visible.sync="studentDialog.show">
      <div slot="title" class="header-title">
        <span>{{ studentDialog.title }}</span>
      </div>
      <el-table ref="studentTable" :data="studentDialog.tableData" max-height="400px">
        <el-table-column prop="studentName" label="学生名称" sortable/>
        <el-table-column prop="className" label="班级名称" sortable/>
        <el-table-column prop="courseName" label="课程名称" sortable/>
        <el-table-column prop="score" label="学分">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.score" :min="0" :max="100" size="small"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="small"
              type="text"
              @click="scoreToStudent(scope.$index, scope.row)"
            >保存分数</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="studentDialog.show = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  data() {
    return {
      tableData: [],
      studentDialog: {
        show: false,
        title: "学生列表",
        tableData: [],
        currentCourse: ""
      }
    };
  },
  created() {
    this.searchForm();
  },
  methods: {
    searchForm() {
      let t = this;
      Vue.axios.post("/teacher/myCourse").then(function(res) {
        if (res.data.code == 200) {
          t.tableData = res.data.entity;
        }
      });
    },
    score(index: any, row: any) {
      let t = this;
      t.studentDialog.currentCourse = row.id;
      t.studentDialog.show = true;
      t.studentDialog.title = "选择课程";
      Vue.axios.post("/teacher/myCourseStudent/" + row.id).then(res => {
        if (res.data.code == 200) {
          t.studentDialog.tableData = res.data.entity;
        }
      });
    },
    scoreToStudent(index: any, row: any) {
      let t = this;
      Vue.axios
        .post("/teacher/scoreToStudent", {
          courseId: t.studentDialog.currentCourse,
          studentId: row.studentId,
          score: row.score
        })
        .then(res => {
          if (res.data.code == 200) {
            t.$message.success(row.studentName + "打分成功");
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
