package com.github.yiyan1992.carloan.entity.school;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * 班级
 */
@Data
@Entity
@Table(name = "school_student")
public class SchoolStudent {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, unique = true, length = 18)
    private String idCard;

    /**
     * 学号
     */
    @Column(nullable = false, unique = true, length = 18)
    private String schoolNo;

    @Column(length = 50)
    private String password;

    @OneToOne
    private SchoolYear schoolYear;

    @ManyToMany
    @JoinTable(name = "school_student_course",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<SchoolCourse> courses;

}
