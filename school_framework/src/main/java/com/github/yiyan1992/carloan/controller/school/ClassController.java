package com.github.yiyan1992.carloan.controller.school;

import com.github.yiyan1992.carloan.entity.query.ClassCourse;
import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolClass;
import com.github.yiyan1992.carloan.service.school.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private SchoolClassService schoolClassService;


    @PostMapping("/list")
    public Response list(SchoolClass schoolClass) {
        Page<SchoolClass> list = schoolClassService.findPageList(schoolClass.getExample(), schoolClass.getPageRequest());
        return Response.of(200, list);
    }

    @PostMapping("/findById/{id}")
    public Response findById(@PathVariable Integer id) {
        Optional<SchoolClass> schoolClass = schoolClassService.findById(id);
        return Response.success(schoolClass.get());
    }

    @PostMapping("/add")
    public Response add(SchoolClass schoolClass) {
        return Response.of(200, schoolClassService.save(schoolClass));
    }

    @PostMapping("/update")
    public Response update(SchoolClass schoolClass) {
        return Response.of(200, schoolClassService.save(schoolClass));
    }

    @PostMapping("/saveClassCourse")
    public Response saveClassCourse(@RequestBody ClassCourse classCourse) {
        return schoolClassService.saveClassCourse(classCourse);
    }

    @PostMapping("/deleteById/{id}")
    public Response delete(@PathVariable Integer id) {
        schoolClassService.deleteById(id);
        return Response.success("");
    }
}
