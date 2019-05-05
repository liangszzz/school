package com.github.yiyan1992.carloan.entity.sys;

import com.github.yiyan1992.carloan.entity.request.Request;
import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 */
@Data
@Entity
@Table(name = "s_menu")
public class Menu extends Request<Menu> implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", length = 11)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    /**
     * 0 菜单 1:按钮
     */
    @Column(name = "type", length = 1)
    private Integer type;

    @Column(name = "url", length = 50)
    private String url;

    @Column(name = "permission", length = 20)
    private String permission;

    @OneToOne
    private Menu menu;

    @Transient
    private boolean hasChildren;

    @Transient
    private int count;

    @Override
    public Example<Menu> getPageExample() {
        return Example.of(this,
                ExampleMatcher.matching()
                        .withMatcher("menuName", matcher -> matcher.contains()));
    }

    public boolean isHasChildren() {
        return count > 0;
    }
}