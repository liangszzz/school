package com.github.yiyan1992.carloan.entity.school;

import com.github.yiyan1992.carloan.entity.request.Request;
import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 教师
 */
@Data
@Entity
@Table(name = "school_teacher")
public class SchoolTeacher extends Request<SchoolTeacher> implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 80)
    private String name;

    /**
     * 工号
     */
    @Column(length = 20, nullable = false, unique = true)
    private String workNo;

    @Column(nullable = false, unique = true, length = 18)
    private String idCard;

    @Column(length = 50)
    private String password;

    /**
     * 一位教师可以教授多门课
     */
    @ManyToMany
    @JoinTable(name = "school_teacher_course",
            joinColumns = {@JoinColumn(name = "teacher_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<SchoolCourse> schoolCourse;

    @Override
    public Example<SchoolTeacher> getPageExample() {
        return Example.of(this,
                ExampleMatcher.matchingAny()
                        .withMatcher("name", matcher -> matcher.contains())
                        .withMatcher("workNo", matcher -> matcher.contains())
                        .withMatcher("idCard", matcher -> matcher.contains())
        );
    }
}
