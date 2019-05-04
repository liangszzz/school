package com.github.yiyan1992.carloan.controller.school;

import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolTeacher;
import com.github.yiyan1992.carloan.service.school.SchoolTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private SchoolTeacherService schoolTeacherService;


    @PostMapping("/list")
    public Response list(SchoolTeacher schoolTeacher) {
        Page<SchoolTeacher> list = schoolTeacherService.findPageList(schoolTeacher.getPageExample(), schoolTeacher.getPageRequest());
        return Response.of(200, list);
    }

    @PostMapping("/findClassById/{id}")
    public Response findClassById(@PathVariable Integer id) {
        Optional<SchoolTeacher> optional = schoolTeacherService.findById(id);
        return Response.SUCCESS(optional.get());
    }

    @PostMapping("/add")
    public Response add(SchoolTeacher schoolYear) {
        return Response.of(200, schoolTeacherService.save(schoolYear));
    }

    @PostMapping("/update")
    public Response update(SchoolTeacher schoolTeacher) {
        return Response.of(200, schoolTeacherService.save(schoolTeacher));
    }

    @PostMapping("/deleteById/{id}")
    public Response delete(@PathVariable Integer id) {
        schoolTeacherService.deleteById(id);
        return Response.SUCCESS("");
    }
}
