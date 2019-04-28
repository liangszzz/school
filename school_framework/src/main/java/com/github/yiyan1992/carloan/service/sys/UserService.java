package com.github.yiyan1992.carloan.service.sys;

import com.github.yiyan1992.carloan.dao.sys.UserDao;
import com.github.yiyan1992.carloan.entity.sys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Cacheable(cacheNames = "users", key = "#username")
    public Optional<User> findUserByName(String username) {
        return userDao.findById(username);
    }

    @CacheEvict(cacheNames = "users", key = "#user.username")
    public User save(User user) {
        return userDao.save(user);
    }

    @CacheEvict(cacheNames = "users", allEntries = true)
    public void flush() {
        System.out.println("flush cache users success");
    }

    public Page<User> findListByUser(Example user, PageRequest pageRequest) {
        return userDao.findAll(user, pageRequest);
    }

    public void delete(User user) {
        userDao.delete(user);
    }
}
