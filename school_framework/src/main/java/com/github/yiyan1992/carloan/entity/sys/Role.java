package com.github.yiyan1992.carloan.entity.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.yiyan1992.carloan.entity.request.Request;
import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * @author admin
 */
@Data
@Entity
@Table(name = "s_role")
public class Role extends Request<Role> implements Serializable {

    @Id
    @Column(name = "role_name", length = 50)
    private String roleName;

    @Column(name = "role_desc", length = 200)
    private String roleDesc;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "s_role_menu",
            joinColumns = {@JoinColumn(name = "role_name", foreignKey = @ForeignKey(name = "role_name"))},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", foreignKey = @ForeignKey(name = "id"))}
    )
    private Set<Menu> menus;

    @Override
    public Example<Role> getExample() {
        return Example.of(this,
                ExampleMatcher.matching()
                        .withMatcher("roleName", matcher -> matcher.contains()));
    }
}
