package com.github.yiyan1992.carloan.dao.sys;

import com.github.yiyan1992.carloan.entity.sys.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, String> {

}
