package com.github.yiyan1992.carloan.controller.school;

import com.github.yiyan1992.carloan.config.MagicValue;
import com.github.yiyan1992.carloan.entity.request.CourseTeacher;
import com.github.yiyan1992.carloan.entity.base.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolCourse;
import com.github.yiyan1992.carloan.entity.school.SchoolYear;
import com.github.yiyan1992.carloan.service.school.SchoolCourseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private SchoolCourseService schoolCourseService;

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/list")
    public Response list(SchoolCourse schoolCourse) {
        Page<SchoolCourse> list = schoolCourseService.findPageList(schoolCourse.getExample(), schoolCourse.getPageRequest());
        return Response.of(200, list);
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/allByYear")
    public Response allByYear(SchoolYear schoolYear) {
        return Response.success(schoolCourseService.findAllByYear(schoolYear));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/findById/{id}")
    public Response findById(@PathVariable Integer id) {
        Optional<SchoolCourse> optional = schoolCourseService.findById(id);
        return Response.success(optional.get());
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/add")
    public Response add(SchoolCourse schoolCourse) {
        return Response.of(200, schoolCourseService.save(schoolCourse));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/update")
    public Response update(SchoolCourse schoolCourse) {
        return Response.of(200, schoolCourseService.save(schoolCourse));
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/saveCourseTeacher")
    public Response saveCourseTeacher(@RequestBody CourseTeacher courseTeacher) {
        return schoolCourseService.saveCourseTeacher(courseTeacher);
    }

    @RequiresRoles(MagicValue.LOGIN_TYPE_MANAGE)
    @PostMapping("/deleteById/{id}")
    public Response delete(@PathVariable Integer id) {
        schoolCourseService.deleteById(id);
        return Response.success("");
    }
}
