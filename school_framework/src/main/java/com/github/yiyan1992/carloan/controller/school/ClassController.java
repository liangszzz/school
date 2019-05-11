package com.github.yiyan1992.carloan.controller.school;

import com.github.yiyan1992.carloan.config.MagicValue;
import com.github.yiyan1992.carloan.entity.request.ClassCourse;
import com.github.yiyan1992.carloan.entity.base.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolClass;
import com.github.yiyan1992.carloan.service.school.SchoolClassService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/list")
    public Response list(SchoolClass schoolClass) {
        Page<SchoolClass> list = schoolClassService.findPageList(schoolClass.getExample(), schoolClass.getPageRequest());
        return Response.of(200, list);
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/all")
    public Response all(SchoolClass schoolClass) {
        return Response.success(schoolClassService.findAll(schoolClass));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/findById/{id}")
    public Response findById(@PathVariable Integer id) {
        Optional<SchoolClass> schoolClass = schoolClassService.findById(id);
        return Response.success(schoolClass.get());
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/add")
    public Response add(SchoolClass schoolClass) {
        return Response.of(200, schoolClassService.save(schoolClass));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/update")
    public Response update(SchoolClass schoolClass) {
        return Response.of(200, schoolClassService.save(schoolClass));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/saveClassCourse")
    public Response saveClassCourse(@RequestBody ClassCourse classCourse) {
        return schoolClassService.saveClassCourse(classCourse);
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/saveClassCourseTeacher")
    public Response saveClassCourseTeacher(Integer courseId, Integer classId, Integer teacherId) {
        return schoolClassService.saveClassCourseTeacher(courseId, classId, teacherId);
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/deleteById/{id}")
    public Response delete(@PathVariable Integer id) {
        schoolClassService.deleteById(id);
        return Response.success("");
    }
}
