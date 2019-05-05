package com.github.yiyan1992.carloan.service;

import com.github.yiyan1992.carloan.entity.sys.Menu;
import com.github.yiyan1992.carloan.entity.sys.Role;
import com.github.yiyan1992.carloan.entity.sys.User;
import com.github.yiyan1992.carloan.service.sys.MenuService;
import com.github.yiyan1992.carloan.service.sys.RoleService;
import com.github.yiyan1992.carloan.service.sys.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysServiceTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Before
    public void init() {
        //插入用户
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setRemember(0);
        userService.save(user);
        //插入角色
        Role role = new Role();
        role.setRoleName("admin");
        role.setRoleDesc("admin role");
        roleService.save(role);
        //插入菜单
        Menu menu = new Menu();
        menu.setId(1);
        menu.setName("userManage");
        menu.setUrl("/userManage");
        menu.setPermission("user:query");
        menuService.save(menu);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.save(user);
        Set<Menu> menus = new HashSet<>();
        menus.add(menu);
        role.setMenus(menus);
        roleService.save(role);
    }


    @Test
    public void findUserByName() {
        Optional<User> a = userService.findUserByName("admin");
        if (a.isPresent()) {
            Set<Role> roles = a.get().getRoles();
            System.out.println(roles);
        }
    }
}