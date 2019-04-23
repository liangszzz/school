package com.github.yiyan1992.carloan.entity.school;

import lombok.Data;

import javax.persistence.*;

/**
 * 课程
 */
@Data
@Entity
@Table(name = "school_course")
public class SchoolCourse {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false,length = 80)
    private String name;
    /**
     * 学分
     */
    @Column
    private Integer score;

    /**
     * 课时
     */
    @Column
    private Integer hour;

    @OneToOne
    private SchoolYear schoolYear;

}
