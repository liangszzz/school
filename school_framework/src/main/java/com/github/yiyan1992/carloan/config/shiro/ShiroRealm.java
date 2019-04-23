package com.github.yiyan1992.carloan.config.shiro;

import com.github.yiyan1992.carloan.config.MagicValue;
import com.github.yiyan1992.carloan.entity.school.SchoolStudent;
import com.github.yiyan1992.carloan.entity.school.SchoolTeacher;
import com.github.yiyan1992.carloan.entity.sys.Menu;
import com.github.yiyan1992.carloan.entity.sys.Role;
import com.github.yiyan1992.carloan.entity.sys.User;
import com.github.yiyan1992.carloan.service.school.SchoolStudentService;
import com.github.yiyan1992.carloan.service.school.SchoolTeacherService;
import com.github.yiyan1992.carloan.service.sys.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.stream.Collectors;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private SchoolTeacherService schoolTeacherService;

    @Autowired
    private SchoolStudentService schoolStudentService;

    /**
     * 登陆
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        SimpleAuthenticationInfo authenticationInfo = null;
        if (isManage(username)) {
            username = username.substring(MagicValue.LOGIN_TYPE_0.length());
            Optional<User> user = userService.findUserByName(username);
            if (user.isPresent()) {
                authenticationInfo = new SimpleAuthenticationInfo(
                        username,
                        user.get().getPassword(),
                        getName()
                );

            }
        } else if (isTeacher(username)) {
            username = username.substring(MagicValue.LOGIN_TYPE_1.length());
            Optional<SchoolTeacher> user = schoolTeacherService.findUserByWorkNo(username);
            if (user.isPresent()) {
                authenticationInfo = new SimpleAuthenticationInfo(
                        username,
                        user.get().getPassword(),
                        getName()
                );
            }
        } else if (isStudent(username)) {
            username = username.substring(MagicValue.LOGIN_TYPE_2.length());
            Optional<SchoolStudent> user = schoolStudentService.findUserBySchoolNo(username);
            if (user.isPresent()) {
                authenticationInfo = new SimpleAuthenticationInfo(
                        username,
                        user.get().getPassword(),
                        getName()
                );
            }
        }
        return authenticationInfo;
    }


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (isManage(username)) {
            Optional<User> user = userService.findUserByName(username.substring(MagicValue.LOGIN_TYPE_0.length()));
            if (user.isPresent()) {
                for (Role role : user.get().getRoles()) {
                    info.addStringPermissions(role.getMenus().stream().map(Menu::getPermission).collect(Collectors.toSet()));
                }
            }
        } else if (isTeacher(username)) {
            info.addRole(MagicValue.LOGIN_TYPE_1);
        } else if (isStudent(username)) {
            info.addRole(MagicValue.LOGIN_TYPE_2);
        }
        return info;
    }

    private boolean isManage(String username) {
        return getUserType(username) == 0;
    }

    private boolean isTeacher(String username) {
        return getUserType(username) == 1;
    }

    private boolean isStudent(String username) {
        return getUserType(username) == 2;
    }

    /**
     * 返回用户类型
     * 0:管理员
     * 1:教师
     * 2:学生
     * -1:未知
     *
     * @param usename
     * @return
     */
    private int getUserType(String usename) {
        if (usename.startsWith(MagicValue.LOGIN_TYPE_0)) {
            return 0;
        } else if (usename.startsWith(MagicValue.LOGIN_TYPE_1)) {
            return 1;
        } else if (usename.startsWith(MagicValue.LOGIN_TYPE_2)) {
            return 2;
        }
        return -1;
    }


}
