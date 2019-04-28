package com.github.yiyan1992.carloan.dao.sys;

import com.github.yiyan1992.carloan.entity.sys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {

}
