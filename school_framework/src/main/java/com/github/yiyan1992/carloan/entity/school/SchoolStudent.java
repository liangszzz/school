package com.github.yiyan1992.carloan.entity.school;


import com.github.yiyan1992.carloan.entity.base.Request;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 班级
 */
@Getter
@Setter
@Entity
@Table(name = "school_student")
public class SchoolStudent extends Request<SchoolStudent> implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, unique = true, length = 18)
    private String idCard;

    @Column(nullable = false, unique = true, length = 18)
    private String schoolNo;

    @Column(length = 50)
    private String password;

    @OneToOne
    private SchoolYear schoolYear;

    @OneToOne
    private SchoolClass schoolClass;

    @Override
    public Example<SchoolStudent> getExample() {
        return Example.of(this, ExampleMatcher.matching()
                        .withMatcher("name", matcher -> matcher.contains())
                        .withMatcher("idCard", matcher -> matcher.contains())
                        .withMatcher("schoolNo", matcher -> matcher.contains())
        );
    }
}
