package com.github.yiyan1992.carloan.controller.school;

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
@RequestMapping("/year")
public class YearController {

    @Autowired
    private UserService userService;


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

    @PostMapping("/add")
    public Response add(User user) {
        user.setPassword("default123456");
        return Response.of(200, userService.save(user));
    }

    @PostMapping("/update")
    public Response update(User user) {
        Optional<User> userSql = userService.findUserByName(user.getUsername());
        if (!userSql.isPresent()) {
            return Response.of(500, "no this user");
        }
        user.setPassword(userSql.get().getPassword());
        return Response.of(200, userService.save(user));
    }

    @PostMapping("/deleteByUsername/{username}")
    public Response delete(@PathVariable String username) {
        userService.deleteByUsername(username);
        return Response.SUCCESS("");
    }
}
