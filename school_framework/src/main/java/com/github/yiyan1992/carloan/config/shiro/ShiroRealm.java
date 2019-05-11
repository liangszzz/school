package com.github.yiyan1992.carloan.config.shiro;

import com.github.yiyan1992.carloan.config.MagicValue;
import com.github.yiyan1992.carloan.entity.school.SchoolStudent;
import com.github.yiyan1992.carloan.entity.school.SchoolTeacher;
import com.github.yiyan1992.carloan.entity.sys.ShiroUser;
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


        if (MagicValue.isManage(username)) {
            username = username.substring(MagicValue.LOGIN_TYPE_MANAGE.length());
            Optional<User> user = userService.findUserByName(username);
            if (user.isPresent()) {
                ShiroUser shiroUser = new ShiroUser();
                shiroUser.setName(user.get().getName());
                shiroUser.setUserType("管理员:");
                shiroUser.setObject(user.get());

                authenticationInfo = new SimpleAuthenticationInfo(
                        shiroUser,
                        user.get().getPassword(),
                        getName()
                );
            }
        } else if (MagicValue.isTeacher(username)) {
            username = username.substring(MagicValue.LOGIN_TYPE_TEACHER.length());
            Optional<SchoolTeacher> user = schoolTeacherService.findUserByWorkNo(username);
            if (user.isPresent()) {
                ShiroUser shiroUser = new ShiroUser();
                shiroUser.setName(user.get().getName());
                shiroUser.setUserType("老师:");
                shiroUser.setObject(user.get());

                authenticationInfo = new SimpleAuthenticationInfo(
                        shiroUser,
                        user.get().getPassword(),
                        getName()
                );
            } else
                return null;
        } else if (MagicValue.isStudent(username)) {
            username = username.substring(MagicValue.LOGIN_TYPE_STUDENT.length());
            Optional<SchoolStudent> user = schoolStudentService.findUserBySchoolNo(username);
            if (user.isPresent()) {
                ShiroUser shiroUser = new ShiroUser();
                shiroUser.setName(user.get().getName());
                shiroUser.setUserType("学生:");
                shiroUser.setObject(user.get());

                authenticationInfo = new SimpleAuthenticationInfo(
                        shiroUser,
                        user.get().getPassword(),
                        getName()
                );
            } else
                return null;
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
        ShiroUser user = (ShiroUser) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        if (user.getObject() instanceof User) {
            info.addRole(MagicValue.LOGIN_TYPE_MANAGE);
//            for (Role role : ((User) user).getRoles()) {
//                info.addStringPermissions(role.getMenus().stream().map(Menu::getPermission).collect(Collectors.toSet()));
//            }
        } else if (user.getObject() instanceof SchoolTeacher) {
            info.addRole(MagicValue.LOGIN_TYPE_TEACHER);
        } else if (user.getObject() instanceof SchoolStudent) {
            info.addRole(MagicValue.LOGIN_TYPE_STUDENT);
        }

        return info;
    }

}
