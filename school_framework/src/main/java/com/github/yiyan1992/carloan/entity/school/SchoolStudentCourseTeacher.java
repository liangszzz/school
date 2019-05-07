package com.github.yiyan1992.carloan.entity.school;

import lombok.Data;

import javax.persistence.*;

/**
 * 学生 课程 教师 关联表
 */
@Data
@Entity
@Table(name = "school_student_course_teacher")
public class SchoolStudentCourseTeacher {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private SchoolStudent schoolStudent;

    @OneToOne
    private SchoolCourse schoolCourse;

    @OneToOne
    private SchoolTeacher schoolTeacher;
}
