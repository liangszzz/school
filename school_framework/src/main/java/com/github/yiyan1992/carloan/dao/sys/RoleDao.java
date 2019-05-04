package com.github.yiyan1992.carloan.dao.sys;

import com.github.yiyan1992.carloan.entity.sys.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends JpaRepository<Role, String> {

}
