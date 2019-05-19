package com.github.yiyan1992.carloan.service.sys;

import com.github.yiyan1992.carloan.dao.sys.RoleDao;
import com.github.yiyan1992.carloan.dao.sys.UserDao;
import com.github.yiyan1992.carloan.entity.exception.NoFindDataException;
import com.github.yiyan1992.carloan.entity.sys.Role;
import com.github.yiyan1992.carloan.entity.sys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Cacheable(cacheNames = "roles", key = "#roleName")
    public Optional<Role> findRole(String roleName) {
        return roleDao.findById(roleName);
    }

    @CacheEvict(cacheNames = "roles", key = "#role.roleName")
    public Role save(Role role) {
        return roleDao.save(role);
    }

    public Page<Role> findPageList(Example<Role> pageExample, PageRequest pageRequest) {
        return roleDao.findAll(pageExample, pageRequest);
    }

    public Optional<Role> findById(String roleName) {
        return roleDao.findById(roleName);
    }

    public void deleteById(String roleName) {
        roleDao.deleteById(roleName);
    }

    public Set<Role> findByUsername(String username) {
        Optional<User> user = userDao.findById(username);
        if (user.isEmpty()) {
            throw new NoFindDataException("用户");
        }
        return user.get().getRoles();
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
