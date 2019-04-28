package com.github.yiyan1992.carloan.controller.web;

import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.sys.User;
import com.github.yiyan1992.carloan.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    public Response list(User user) {
        Page<User> listByUser = userService.findListByUser(user.getPageExample(), user.getPageRequest());
        return Response.of(listByUser);
    }
}
