package com.github.yiyan1992.carloan.entity.school;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 学生 课程 教师 关联表
 */
@Getter
@Setter
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

    @Column(length = 2)
    private Integer score;
}
