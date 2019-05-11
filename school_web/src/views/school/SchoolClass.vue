<template>
    <div id="app">
        <el-row>
            <el-breadcrumb separator="/" style="height: 40px;">
                <el-breadcrumb-item>校园管理</el-breadcrumb-item>
                <el-breadcrumb-item>班级管理</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
        <el-row>
            <el-form :model="queryForm" ref="queryForm" label-width="110px">
                <el-col :span="6">
                    <el-form-item label="班级名称">
                        <el-input v-model="queryForm.name" clearable></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="学年">
                        <el-select v-model="queryForm['schoolYear.id']" filterable clearable placeholder="请输入关键词">
                            <el-option v-for="item in formSelect.options" :key="item.id" :label="item.name"
                                       :value="item.id">
                            </el-option>
                        </el-select>
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
                <el-table-column prop="name" label="班级名称"/>
                <el-table-column prop="schoolYear.name" label="学年名称"/>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button @click="toShow(scope.$index, scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="toUpdate(scope.$index, scope.row)" type="text" size="small">编辑</el-button>
                        <el-button @click="toCourse(scope.$index, scope.row)" type="text" size="small">课程</el-button>
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
                <el-form-item label="名称" prop="name">
                    <el-input v-model="dialog.form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="学年" prop="schoolYear">
                    <el-select v-model="dialog.form.schoolYear" filterable clearable placeholder="请输入关键词">
                        <el-option v-for="item in formSelect.options" :key="item.id" :label="item.name"
                                   :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialog.show = false">取 消</el-button>
                <el-button type="primary" @click="validate">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog :visible.sync="courseDialog.show">
            <div slot="title" class="header-title">
                <span> {{ courseDialog.title }}</span>
            </div>
            <el-table ref="courseTable" :data="courseDialog.tableData" max-height="400px"
                      @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55"/>
                <el-table-column prop="name" label="课程名称" sortable/>
                <el-table-column prop="score" label="学分" sortable/>
                <el-table-column prop="hour" label="课时" sortable/>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button @click="toCourseTeacher(scope.$index, scope.row)" type="text" size="small">选择老师
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div slot="footer" class="dialog-footer">
                <el-button @click="courseDialog.show = false">取 消</el-button>
                <el-button type="primary" @click="saveCourse">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog :visible.sync="courseTeacherDialog.show">
            <div slot="title" class="header-title">
                <span> {{ courseTeacherDialog.title }}</span>
            </div>
            <el-row>
                <el-tag>{{courseTeacherDialog.currentCourseName}}</el-tag>
                <el-tag> 课程的老师是</el-tag>
                <el-tag>[{{courseTeacherDialog.currentCourseTeacherName}}]</el-tag>
            </el-row>
            <el-table ref="courseTeacherTable" :data="courseTeacherDialog.tableData" max-height="400px"
                      highlight-current-row
                      @current-change="handleCurrentRowChange">
                <el-table-column prop="name" label="教师名称"/>
                <el-table-column prop="workNo" label="教师工号"/>
                <el-table-column prop="idCard" label="教师身份证号"/>
            </el-table>
            <div slot="footer" class="dialog-footer">
                <el-button @click="clearTableSelect">清除选择</el-button>
                <el-button @click="courseTeacherDialog.show = false">取 消</el-button>
                <el-button type="primary" @click="saveCourseTeacher">确 定</el-button>
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
                    "schoolYear.id": "",
                    page: 1,
                    size: 10,
                    total: 0,
                },
                formSelect: {
                    options: []
                },
                tableData: [],
                dialog: {
                    saveType: 0,
                    title: "添加",
                    show: false,
                    form: {
                        id: "",
                        name: "",
                        schoolYear: "",
                    },
                    rules: {
                        name: [
                            {required: true, message: '请输入名称', trigger: 'blur'}],
                    }
                },
                courseDialog: {
                    currentClass: "",
                    title: "选择课程",
                    show: false,
                    tableData: [],
                    multipleSelection: []
                },
                courseTeacherDialog: {
                    currentClass: "",
                    currentCourse: "",
                    currentCourseName: "",
                    currentCourseTeacherName: "未选择",
                    title: "为班级的课程分配老师",
                    show: false,
                    tableData: [],
                    currentRow: null,
                }
            }
        },
        created() {
            this.searchForm("queryForm");
            this.loadQueryFormSelect();
        },
        methods: {
            loadQueryFormSelect() {
                let t = this;
                Vue.axios.post("year/all").then(res => {
                    if (res.data.code == 200) {
                        t.formSelect.options = res.data.entity;
                    }
                });
            },
            handleSizeChange(val: number) {
                this.queryForm.size = val;
                this.searchForm('queryForm');
            },
            handleCurrentChange(val: number) {
                this.queryForm.page = val;
                this.searchForm('queryForm');
            },
            handleSelectionChange(val: any) {
                this.courseDialog.multipleSelection = val;
            },
            handleCurrentRowChange(val: any) {
                this.courseTeacherDialog.currentRow = val;
            },
            clearTableSelect() {
                this.$refs.courseTeacherTable.setCurrentRow();
            },
            searchForm(formName: string) {
                let t = this;
                Vue.axios.post("/class/list", t.$data[formName]).then(function (res) {
                    if (res.data.code == 200) {
                        t.tableData = res.data.entity.content;
                        t.queryForm.page = res.data.entity.pageable.pageNumber+1
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
                    schoolYear: "",
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
            toCourse(index: number, row: any) {
                let t = this;
                t.courseDialog.currentClass = row.id;
                t.courseTeacherDialog.currentClass = row.id;
                t.courseTeacherDialog.currentCourseName = row.name;
                t.courseDialog.show = true;
                t.courseDialog.title = "选择课程";

                Vue.axios.post("/course/allByYear/", {
                    id: row.schoolYear.id
                }).then(res => {
                    if (res.data.code == 200)
                        t.courseDialog.tableData = res.data.entity;
                });
                setTimeout(() => {
                    Vue.axios.post("/class/findById/" + row.id).then(function (res) {
                        if (res.data.code == 200) {
                            t.$refs.courseTable.clearSelection();
                            let courses = res.data.entity.courses;
                            courses.forEach(value => {
                                let index = t.courseDialog.tableData.findIndex((value1: never, index: number, obj: never[]) => {
                                    return value1.id === value.id;
                                });
                                t.$refs.courseTable.toggleRowSelection(t.courseDialog.tableData[index]);
                            })
                        }
                    })
                }, 500);
            },
            saveCourse() {
                let t = this;
                let class_id = t.courseDialog.currentClass;
                let courses: Number[] = [];
                if (t.courseDialog.multipleSelection.length == 0) {
                    t.$message({
                        message: '没有选择老师!',
                        type: 'warning',
                        showClose: true,
                    });
                }
                t.courseDialog.multipleSelection.forEach(e => {
                    courses.push(e.id);
                })
                Vue.axios.post("/class/saveClassCourse", {
                    id: class_id,
                    courses: courses,
                }, {headers: {"content-type": "application/json",}}).then(res => {
                    if (res.data.code == 200) {
                        t.$message({
                            message: '保存成功!',
                            type: 'success',
                            showClose: true,
                        });
                        t.courseDialog.show = false;
                    }
                });
            },
            toCourseTeacher(index: number, row: any) {
                let t = this;
                t.courseTeacherDialog.currentCourse = row.id;
                t.courseTeacherDialog.show = true;
                t.courseTeacherDialog.currentCourseTeacherName = "未选择";

                Vue.axios.post("/teacher/allByCourse/" + row.id).then(res => {
                    if (res.data.code == 200)
                        t.courseTeacherDialog.tableData = res.data.entity;
                });
                setTimeout(() => {
                    Vue.axios.post("/teacher/findByCourseAndClass", {
                        classId: t.courseTeacherDialog.currentClass,
                        courseId: row.id
                    }).then(function (res) {
                        if (res.data.code == 200) {
                            let teacher = res.data.entity;
                            t.courseTeacherDialog.currentCourseTeacherName = teacher.name;
                            let index = t.courseTeacherDialog.tableData.findIndex((value: never, index: number, obj: never[]) => {
                                return value.id === teacher.id;
                            });
                            t.$refs.courseTeacherTable.setCurrentRow(t.courseTeacherDialog.tableData[index]);
                        }
                    })
                }, 500);
            },
            saveCourseTeacher() {
                let t = this;
                let classId = t.courseTeacherDialog.currentClass;
                let courseId = t.courseTeacherDialog.currentCourse;
                let teacherId = null;
                let teacher = t.courseTeacherDialog.currentRow;
                if (teacher != null) {
                    teacherId = teacher.id;
                }
                if (teacher == null) {
                    t.$message({
                        message: '没有选择老师!',
                        type: 'warning',
                        showClose: true,
                    });
                }

                Vue.axios.post("/class/saveClassCourseTeacher", {
                    classId: classId,
                    courseId: courseId,
                    teacherId: teacherId,
                }).then(res => {
                    if (res.data.code == 200) {
                        t.$message({
                            message: '保存成功!',
                            type: 'success',
                            showClose: true,
                        });
                        t.courseTeacherDialog.show = false;
                        t.courseDialog.show = false;
                    }
                });
            },


            toDelete(index: number, row: any) {
                let t = this;
                Vue.axios.post("/class/deleteById/" + row.id).then(function (res) {
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
                Vue.axios.post("/class/findById/" + id).then(function (res) {
                    let data = res.data;
                    if (data.code == 200) {
                        t.dialog.form = {
                            id: data.entity.id,
                            name: data.entity.name,
                            schoolYear: data.entity.schoolYear.id,
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
                let url
                if (this.dialog.saveType === 1) {
                    url = "/class/add";
                } else if (t.dialog.saveType === 2) {
                    url = "/class/update";
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

    .el-select {
        width: 100%;
    }
</style>
