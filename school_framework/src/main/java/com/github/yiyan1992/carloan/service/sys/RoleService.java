package com.github.yiyan1992.carloan.service.sys;

import com.github.yiyan1992.carloan.dao.sys.RoleDao;
import com.github.yiyan1992.carloan.entity.sys.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    @Cacheable(cacheNames = "roles", key = "#roleName")
    public Optional<Role> findRole(String roleName) {
        return roleDao.findById(roleName);
    }

    @CacheEvict(cacheNames = "roles", key = "#role.roleName")
    public void save(Role role) {
        roleDao.save(role);
    }
}
