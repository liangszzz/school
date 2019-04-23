package com.github.yiyan1992.carloan.entity.school;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * 班级
 */
@Data
@Entity
@Table(name = "school_class")
public class SchoolClass {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 80)
    private String name;

    @OneToOne
    private SchoolYear schoolYear;

    /**
     * 固定教室
     */
    @OneToOne
    private SchoolRoom schoolRoom;

    /**
     * 班级课程
     */
    @ManyToMany
    @JoinTable(name = "school_class_course",
            joinColumns = {@JoinColumn(name = "class_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<SchoolCourse> courses;

    /**
     * 班级学生
     */
    @ManyToMany
    @JoinTable(name = "school_class_student",
            joinColumns = {@JoinColumn(name = "class_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<SchoolStudent> students;

}
