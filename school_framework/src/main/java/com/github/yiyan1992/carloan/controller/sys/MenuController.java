package com.github.yiyan1992.carloan.controller.sys;

import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.sys.Menu;
import com.github.yiyan1992.carloan.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @PostMapping("/list")
    public Response list() {
        return Response.of(200, menuService.findList());
    }

    @PostMapping("/loadChildren/{id}")
    public Response loadChildren(@PathVariable Integer id) {
        return Response.of(200, menuService.findChildrenList(id));
    }

    @PostMapping("/findById/{id}")
    public Response findClassById(@PathVariable Integer id) {
        Optional<Menu> menu = menuService.findById(id);
        return Response.SUCCESS(menu.get());
    }

    @PostMapping("/add")
    public Response add(Menu menu) {
        return Response.of(200, menuService.save(menu));
    }

    @PostMapping("/update")
    public Response update(Menu menu) {
        return Response.of(200, menuService.save(menu));
    }

    @PostMapping("/deleteById/{id}")
    public Response delete(@PathVariable Integer id) {
        menuService.deleteById(id);
        return Response.SUCCESS("");
    }
}
