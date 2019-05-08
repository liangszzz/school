package com.github.yiyan1992.carloan.entity.school;

import lombok.Data;

import javax.persistence.*;

/**
 * 班级 课程 教师 关联表
 */
@Data
@Entity
@Table(name = "school_class_course_teacher")
public class SchoolClassCourseTeacher {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private SchoolClass schoolClass;

    @OneToOne
    private SchoolCourse schoolCourse;

    @OneToOne
    private SchoolTeacher schoolTeacher;
}
