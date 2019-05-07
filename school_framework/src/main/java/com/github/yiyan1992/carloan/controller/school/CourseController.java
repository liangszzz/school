package com.github.yiyan1992.carloan.controller.school;

import com.github.yiyan1992.carloan.entity.query.CourseTeacher;
import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolCourse;
import com.github.yiyan1992.carloan.entity.school.SchoolYear;
import com.github.yiyan1992.carloan.service.school.SchoolCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private SchoolCourseService schoolCourseService;

    @PostMapping("/list")
    public Response list(SchoolCourse schoolCourse) {
        Page<SchoolCourse> list = schoolCourseService.findPageList(schoolCourse.getExample(), schoolCourse.getPageRequest());
        return Response.of(200, list);
    }

    @PostMapping("/allByYear")
    public Response allByYear(SchoolYear schoolYear) {
        return Response.success(schoolCourseService.findAllByYear(schoolYear));
    }

    @PostMapping("/findById/{id}")
    public Response findById(@PathVariable Integer id) {
        Optional<SchoolCourse> optional = schoolCourseService.findById(id);
        return Response.success(optional.get());
    }

    @PostMapping("/add")
    public Response add(SchoolCourse schoolCourse) {
        return Response.of(200, schoolCourseService.save(schoolCourse));
    }

    @PostMapping("/update")
    public Response update(SchoolCourse schoolCourse) {
        return Response.of(200, schoolCourseService.save(schoolCourse));
    }

    @PostMapping("/saveCourseTeacher")
    public Response saveCourseTeacher(@RequestBody CourseTeacher courseTeacher) {
        return schoolCourseService.saveCourseTeacher(courseTeacher);
    }

    @PostMapping("/deleteById/{id}")
    public Response delete(@PathVariable Integer id) {
        schoolCourseService.deleteById(id);
        return Response.success("");
    }
}
