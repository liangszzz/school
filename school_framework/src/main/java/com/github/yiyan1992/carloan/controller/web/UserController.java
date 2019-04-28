package com.github.yiyan1992.carloan.controller.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.sys.User;
import com.github.yiyan1992.carloan.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    public Response list(User user) {
        Page<User> listByUser = userService.findListByUser(user.getPageExample(), user.getPageRequest());
        return Response.of(200, listByUser);
    }

    @PostMapping("/updatePwd")
    public Response updatePwd(String oldPwd, String newPwd) {

        return Response.SUCCESS("");
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
