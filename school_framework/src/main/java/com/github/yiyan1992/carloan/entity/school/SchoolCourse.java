package com.github.yiyan1992.carloan.entity.school;

import com.github.yiyan1992.carloan.entity.request.Request;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 课程
 */
@Getter
@Setter
@Entity
@Table(name = "school_course")
public class SchoolCourse extends Request<SchoolCourse> implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 80)
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

    /**
     * 一位教师可以教授多门课
     */
    @ManyToMany
    @JoinTable(name = "school_course_teacher",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    private Set<SchoolTeacher> schoolTeachers;

    @Override
    public Example<SchoolCourse> getExample() {
        return Example.of(this,
                ExampleMatcher.matching()
                        .withMatcher("name", matcher -> matcher.contains())
        );
    }
}
