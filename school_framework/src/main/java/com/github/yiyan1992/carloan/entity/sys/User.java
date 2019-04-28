package com.github.yiyan1992.carloan.entity.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.yiyan1992.carloan.entity.request.Request;
import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author admin
 */
@Data
@Entity
@Table(name = "s_user")
public class User extends Request<User> implements Serializable {

    @Id
    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "name",length = 50)
    private String name;

    @JsonIgnore
    @Column(name = "password", length = 150)
    private String password;

    @Column(name = "remember", length = 1)
    private Integer remember;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @Column(name = "last_login_ip", length = 17)
    private String lastLoginIp;

    @Column(name = "fail_count")
    private Integer failCount;


    @ManyToMany
    @JoinTable(name = "s_user_role",
            joinColumns = {@JoinColumn(name = "username", foreignKey = @ForeignKey(name = "username"))},
            inverseJoinColumns = {@JoinColumn(name = "role_name", foreignKey = @ForeignKey(name = "role_name"))})
    private Set<Role> roles;

    @Override
    public Example<User> getPageExample() {
        if (StringUtils.isEmpty(username)) {
            Example<User> userExample = Example.of(this, ExampleMatcher.matching().withIgnorePaths("username"));
            return userExample;
        } else {
            Example<User> userExample = Example.of(this,
                    ExampleMatcher.matching()
                            .withMatcher("username", matcher -> matcher.contains()));
            return userExample;
        }
    }
}
