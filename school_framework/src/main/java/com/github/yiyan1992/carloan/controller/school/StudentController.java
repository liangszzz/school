package com.github.yiyan1992.carloan.controller.school;

import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolStudent;
import com.github.yiyan1992.carloan.service.school.SchoolStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private SchoolStudentService schoolStudentService;


    @PostMapping("/list")
    public Response list(SchoolStudent schoolStudent) {
        Page<SchoolStudent> list = schoolStudentService.findPageList(schoolStudent.getPageExample(), schoolStudent.getPageRequest());
        return Response.of(200, list);
    }

    @PostMapping("/findClassById/{id}")
    public Response findClassById(@PathVariable Integer id) {
        Optional<SchoolStudent> optional = schoolStudentService.findById(id);
        return Response.SUCCESS(optional.get());
    }

    @PostMapping("/add")
    public Response add(SchoolStudent schoolStudent) {
        return Response.of(200, schoolStudentService.save(schoolStudent));
    }

    @PostMapping("/update")
    public Response update(SchoolStudent schoolYear) {
        return Response.of(200, schoolStudentService.save(schoolYear));
    }

    @PostMapping("/deleteById/{id}")
    public Response delete(@PathVariable Integer id) {
        schoolStudentService.deleteById(id);
        return Response.SUCCESS("");
    }
}
