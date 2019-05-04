package com.github.yiyan1992.carloan.entity.school;

import com.github.yiyan1992.carloan.entity.request.Request;
import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 课程
 */
@Data
@Entity
@Table(name = "school_course")
public class SchoolCourse extends Request<SchoolCourse> implements Serializable {

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

    @Override
    public Example<SchoolCourse> getPageExample() {
        return Example.of(this,
                ExampleMatcher.matchingAny()
                        .withMatcher("name", matcher -> matcher.contains())
        );
    }
}
