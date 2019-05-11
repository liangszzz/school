package com.github.yiyan1992.carloan.controller.school;

import com.github.yiyan1992.carloan.config.MagicValue;
import com.github.yiyan1992.carloan.entity.base.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolYear;
import com.github.yiyan1992.carloan.service.school.SchoolYearService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/year")
public class YearController {

    @Autowired
    private SchoolYearService schoolYearService;

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/list")
    public Response list(SchoolYear schoolYear) {
        Page<SchoolYear> list = schoolYearService.findPageList(schoolYear.getExample(), schoolYear.getPageRequest());
        return Response.of(200, list);
    }

    @PostMapping("/all")
    public Response all() {
        return Response.success(schoolYearService.findAll());
    }

    @PostMapping("/findById/{id}")
    public Response findById(@PathVariable Integer id) {
        Optional<SchoolYear> optional = schoolYearService.findById(id);
        return Response.success(optional.get());
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/add")
    public Response add(SchoolYear schoolYear) {
        return Response.of(200, schoolYearService.save(schoolYear));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/update")
    public Response update(SchoolYear schoolYear) {
        return Response.of(200, schoolYearService.save(schoolYear));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/deleteById/{id}")
    public Response delete(@PathVariable Integer id) {
        schoolYearService.deleteById(id);
        return Response.success("");
    }
}
