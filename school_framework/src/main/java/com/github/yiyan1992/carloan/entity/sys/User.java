package com.github.yiyan1992.carloan.entity.sys;

import lombok.Data;

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
public class User implements Serializable {

    @Id
    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password", length = 150)
    private String password;

    @Column(name = "remember", length = 1)
    private int remember;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @Column(name = "last_login_ip", length = 17)
    private String lastLoginIp;

    @Column(name = "fail_count")
    private int failCount = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "s_user_role",
            joinColumns = {@JoinColumn(name = "username", foreignKey = @ForeignKey(name = "username"))},
            inverseJoinColumns = {@JoinColumn(name = "role_name", foreignKey = @ForeignKey(name = "role_name"))})
    private Set<Role> roles;
}
