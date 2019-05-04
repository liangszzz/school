package com.github.yiyan1992.carloan.entity.school;

import com.github.yiyan1992.carloan.entity.request.Request;
import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 教室
 */
@Data
@Entity
@Table(name = "school_room")
public class SchoolRoom extends Request<SchoolRoom> implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 80)
    private String name;

    @Override
    public Example<SchoolRoom> getPageExample() {
        return Example.of(this,
                ExampleMatcher.matching()
                        .withMatcher("name", matcher -> matcher.contains()));
    }
}
