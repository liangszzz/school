package com.github.yiyan1992.carloan.controller.school;

import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolYear;
import com.github.yiyan1992.carloan.service.school.SchoolYearService;
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


    @PostMapping("/list")
    public Response list(SchoolYear schoolYear) {
        Page<SchoolYear> list = schoolYearService.findPageList(schoolYear.getPageExample(), schoolYear.getPageRequest());
        return Response.of(200, list);
    }

    @PostMapping("/findById/{id}")
    public Response findById(@PathVariable Integer id) {
        Optional<SchoolYear> optional = schoolYearService.findById(id);
        return Response.SUCCESS(optional.get());
    }

    @PostMapping("/add")
    public Response add(SchoolYear schoolYear) {
        return Response.of(200, schoolYearService.save(schoolYear));
    }

    @PostMapping("/update")
    public Response update(SchoolYear schoolYear) {
        return Response.of(200, schoolYearService.save(schoolYear));
    }

    @PostMapping("/deleteById/{id}")
    public Response delete(@PathVariable Integer id) {
        schoolYearService.deleteById(id);
        return Response.SUCCESS("");
    }
}
