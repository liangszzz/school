package com.github.yiyan1992.carloan.controller.sys;

import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.sys.Role;
import com.github.yiyan1992.carloan.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping("/list")
    public Response list(Role role) {
        Page<Role> list = roleService.findPageList(role.getExample(), role.getPageRequest());
        return Response.of(200, list);
    }

    @PostMapping("/findById/{roleName}")
    public Response findClassById(@PathVariable String roleName) {
        Optional<Role> role = roleService.findById(roleName);
        if (role.isPresent()) {
            return Response.success(role.get());
        }
        return Response.of(500, "角色不存在");
    }

    @PostMapping("/add")
    public Response add(Role role) {
        return Response.of(200, roleService.save(role));
    }

    @PostMapping("/update")
    public Response update(Role role) {
        return Response.of(200, roleService.save(role));
    }

    @PostMapping("/deleteById/{roleName}")
    public Response delete(@PathVariable String roleName) {
        roleService.deleteById(roleName);
        return Response.success("");
    }
}
