package com.github.yiyan1992.carloan.controller.web;

import com.github.yiyan1992.carloan.entity.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
public class ManageController {

    @PostMapping("/list")
    public Response list() {
        return Response.SUCCESS();
    }
}
