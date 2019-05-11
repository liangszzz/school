package com.github.yiyan1992.carloan.controller.sys;

import com.github.yiyan1992.carloan.config.MagicValue;
import com.github.yiyan1992.carloan.entity.request.RoleMenu;
import com.github.yiyan1992.carloan.entity.base.Response;
import com.github.yiyan1992.carloan.entity.sys.Menu;
import com.github.yiyan1992.carloan.service.sys.MenuService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/list")
    public Response list() {
        return Response.of(200, menuService.findList());
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/loadChildren/{id}")
    public Response loadChildren(@PathVariable Integer id) {
        return Response.of(200, menuService.findChildrenList(id));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/findRoleMenu/{roleName}")
    public Response findRoleMenu(@PathVariable String roleName) {
        return Response.success(menuService.findMenuByRole(roleName));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/findAll")
    public Response findAll() {
        return Response.success(menuService.findAll());
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/findById/{id}")
    public Response findClassById(@PathVariable Integer id) {
        Optional<Menu> menu = menuService.findById(id);
        return Response.success(menu.get());
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/add")
    public Response add(Menu menu) {
        return Response.of(200, menuService.save(menu));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/update")
    public Response update(Menu menu) {
        return Response.of(200, menuService.save(menu));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/saveRoleMenu")
    public Response saveRoleMenu(@RequestBody RoleMenu roleMenu) {
        return menuService.saveRoleMenu(roleMenu);
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/deleteById/{id}")
    public Response delete(@PathVariable Integer id) {
        menuService.deleteById(id);
        return Response.success("");
    }
}
