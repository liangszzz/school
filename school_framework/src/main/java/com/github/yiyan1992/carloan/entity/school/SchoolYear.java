package com.github.yiyan1992.carloan.entity.school;

import com.github.yiyan1992.carloan.entity.request.Request;
import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 学年
 * 如2019 学年入学
 */
@Data
@Entity
@Table(name = "school_year")
public class SchoolYear extends Request<SchoolYear> implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Override
    public Example<SchoolYear> getPageExample() {
        return Example.of(this,
                ExampleMatcher.matching()
                        .withMatcher("name", matcher -> matcher.contains()));
    }
}
