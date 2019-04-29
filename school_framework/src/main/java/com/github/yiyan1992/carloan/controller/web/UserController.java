package com.github.yiyan1992.carloan.controller.web;

import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.sys.User;
import com.github.yiyan1992.carloan.service.sys.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @return
     */
    @PostMapping(value = "/home")
    public Response home() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        return Response.of(200, username);
    }

    @PostMapping("/list")
    public Response list(User user) {
        Page<User> listByUser = userService.findListByUser(user.getPageExample(), user.getPageRequest());
        return Response.of(200, listByUser);
    }
    @PostMapping("/findUserByUsername/{username}")
    public Response findUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findUserByName(username);
        return Response.SUCCESS(user.get());
    }

    @RequiresAuthentication
    @PostMapping("/updatePwd")
    public Response updatePwd(@RequestParam String password, @RequestParam String password2) {
        if (password.equals(password2)) {
            return Response.SUCCESS("密码保存成功!");
        }
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        Optional<User> user = userService.findUserByName(username);
        if (user.isPresent()) {
            if (password2.equals(user.get().getPassword())) {
                user.get().setPassword(password2);
                userService.save(user.get());
                return Response.SUCCESS("密码保存成功!");
            }
            return Response.of(500, "保存失败!");
        }
        return Response.of(500, "保存失败!");
    }

    @PostMapping("/save")
    public Response save(User user) {
        return Response.of(200, userService.save(user));
    }

    @PostMapping("/delete")
    public Response delete(User user) {
        userService.delete(user);
        return Response.SUCCESS("");
    }
}
