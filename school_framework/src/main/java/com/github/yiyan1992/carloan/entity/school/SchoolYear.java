package com.github.yiyan1992.carloan.entity.school;

import lombok.Data;

import javax.persistence.*;

/**
 * 学年
 * 如2019 学年入学
 */
@Data
@Entity
@Table(name = "school_year")
public class SchoolYear {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;
}
