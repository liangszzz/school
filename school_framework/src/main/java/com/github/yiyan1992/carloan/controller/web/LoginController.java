package com.github.yiyan1992.carloan.controller.web;

import com.github.yiyan1992.carloan.entity.query.Login;
import com.github.yiyan1992.carloan.entity.query.Query2;
import com.github.yiyan1992.carloan.entity.response.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * @author admin
 */
@RestController
public class LoginController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }


    @PostMapping(value = "/login")
    public Response login(Login login) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(login.getUsername(), login.getPassword());
        subject.login(token);
        Response response = Response.SUCCESS();
        response.setEntity(subject.getSession().getId());
        return response;
    }

    @RequiresPermissions("user:query")
    @GetMapping(value = "/query")
    public String query() {
        return "query";
    }


    @GetMapping(value = "/unauth")
    public Response unauth() {
        return Response.of(500, "unauth or error");
    }
}
