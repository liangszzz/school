package com.github.yiyan1992.carloan.entity.school;

import lombok.Data;

import javax.persistence.*;

/**
 * 教室
 */
@Data
@Entity
@Table(name = "school_room")
public class SchoolRoom {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 80)
    private String name;
}
