package com.github.yiyan1992.carloan.controller.sys;

import com.github.yiyan1992.carloan.config.MagicValue;
import com.github.yiyan1992.carloan.entity.base.Response;
import com.github.yiyan1992.carloan.entity.request.UserRole;
import com.github.yiyan1992.carloan.entity.school.SchoolStudent;
import com.github.yiyan1992.carloan.entity.school.SchoolTeacher;
import com.github.yiyan1992.carloan.entity.sys.ShiroUser;
import com.github.yiyan1992.carloan.entity.sys.User;
import com.github.yiyan1992.carloan.service.school.SchoolStudentService;
import com.github.yiyan1992.carloan.service.school.SchoolTeacherService;
import com.github.yiyan1992.carloan.service.sys.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired private UserService userService;

  @Autowired private SchoolTeacherService schoolTeacherService;

  @Autowired private SchoolStudentService schoolStudentService;

  /**
   * 获取用户信息
   *
   * @return
   */
  @PostMapping(value = "/home")
  public Response home() {
    Subject subject = SecurityUtils.getSubject();
    Object user = subject.getPrincipals().getPrimaryPrincipal();
    return Response.of(200, user);
  }

  @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
  @PostMapping("/list")
  public Response list(User user) {
    Page<User> listByUser = userService.findListByUser(user.getExample(), user.getPageRequest());
    return Response.of(200, listByUser);
  }

  @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
  @PostMapping("/findUserByUsername/{username}")
  public Response findUserByUsername(@PathVariable String username) {
    Optional<User> user = userService.findUserByName(username);
    return Response.success(user.get());
  }

  @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
  @RequiresAuthentication
  @PostMapping("/updatePwd")
  public Response updatePwd(@RequestParam String password, @RequestParam String password2) {
    if (password.equals(password2)) {
      return Response.success("密码保存成功!");
    }
    Subject subject = SecurityUtils.getSubject();
    ShiroUser user = (ShiroUser) subject.getPrincipals().getPrimaryPrincipal();
    // 如果管理员
    if (MagicValue.isManage(user.getType())) {
      Optional<User> u = userService.findUserByName(user.getUsername());
      if (u.isPresent()) {
        if (password.equals(u.get().getPassword())) {
          u.get().setPassword(password2);
          userService.save(u.get());
          return Response.success("密码保存成功!");
        }
        return Response.of(500, "保存失败!");
      }
    } else if (MagicValue.isTeacher(user.getType())) {
      Optional<SchoolTeacher> teacher = schoolTeacherService.findUserByWorkNo(user.getUsername());
      if (teacher.isPresent()) {
        if (password.equals(teacher.get().getPassword())) {
          teacher.get().setPassword(password2);
          schoolTeacherService.save(teacher.get());
          return Response.success("密码保存成功!");
        }
      }
    } else if (MagicValue.isStudent(user.getType())) {
      Optional<SchoolStudent> student = schoolStudentService.findUserBySchoolNo(user.getUsername());
      if (student.isPresent()) {
        if (password.equals(student.get().getPassword())) {
          student.get().setPassword(password2);
          schoolStudentService.save(student.get());
          return Response.success("密码保存成功!");
        }
      }
    }
    return Response.of(500, "保存失败!");
  }

  @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
  @PostMapping("/add")
  public Response add(User user) {
    user.setPassword("default123456");
    return Response.of(200, userService.save(user));
  }

  @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
  @PostMapping("/update")
  public Response update(User user) {
    Optional<User> userSql = userService.findUserByName(user.getUsername());
    if (!userSql.isPresent()) {
      return Response.of(500, "no this user");
    }
    user.setPassword(userSql.get().getPassword());
    return Response.of(200, userService.save(user));
  }

  @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
  @PostMapping("/deleteByUsername/{username}")
  public Response delete(@PathVariable String username) {
    userService.deleteByUsername(username);
    return Response.success("");
  }

  @PostMapping("/saveRole")
  public Response saveRole(@RequestBody UserRole userRole) {
    userService.saveRole(userRole);
    return Response.success("");
  }
}
