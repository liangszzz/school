package com.github.yiyan1992.carloan.controller.school;

import com.github.yiyan1992.carloan.config.MagicValue;
import com.github.yiyan1992.carloan.entity.base.Response;
import com.github.yiyan1992.carloan.entity.response.StudentCourseResponse;
import com.github.yiyan1992.carloan.entity.school.SchoolCourse;
import com.github.yiyan1992.carloan.entity.school.SchoolTeacher;
import com.github.yiyan1992.carloan.entity.sys.ShiroUser;
import com.github.yiyan1992.carloan.service.school.SchoolTeacherService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private SchoolTeacherService schoolTeacherService;


    @PostMapping("/list")
    public Response list(SchoolTeacher schoolTeacher) {
        Page<SchoolTeacher> list = schoolTeacherService.findPageList(schoolTeacher.getExample(), schoolTeacher.getPageRequest());
        return Response.of(200, list);
    }

    @PostMapping("/all")
    public Response all() {
        return Response.success(schoolTeacherService.findAll());
    }

    @PostMapping("/allByCourse/{courseId}")
    public Response allByCourse(@PathVariable Integer courseId) {
        return Response.success(schoolTeacherService.findAllByCourse(courseId));
    }

    @PostMapping("/findByCourseAndClass")
    public Response findByCourseAndClass(Integer courseId, Integer classId) {
        return schoolTeacherService.findByCourseAndClass(courseId, classId);
    }

    @PostMapping("/myCourse")
    public Response myCourse() {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipals().getPrimaryPrincipal();
        if (!MagicValue.isTeacher(user.getType())) {
            return Response.error("不是老师,无法使用");
        }
        List<SchoolCourse> list = schoolTeacherService.findAllCourseByWorkNo(user.getUsername());
        return Response.success(list);
    }

    @PostMapping("/myCourseStudent/{courseId}")
    public Response myCourseStudent(@PathVariable Integer courseId) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipals().getPrimaryPrincipal();
        if (!MagicValue.isTeacher(user.getType())) {
            return Response.error("不是老师,无法使用");
        }
        List<StudentCourseResponse> list = schoolTeacherService.findStudentCourseByWorkNo(user.getUsername(), courseId);
        return Response.success(list);
    }

    @PostMapping("/scoreToStudent")
    public Response scoreToStudent(Integer courseId, Integer studentId,Integer score) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipals().getPrimaryPrincipal();
        if (!MagicValue.isTeacher(user.getType())) {
            return Response.error("不是老师,无法使用");
        }
        schoolTeacherService.scoreToStudent(user.getUsername(),courseId,studentId,score);
        return Response.success("");
    }

    @PostMapping("/findById/{id}")
    public Response findById(@PathVariable Integer id) {
        Optional<SchoolTeacher> optional = schoolTeacherService.findById(id);
        return Response.success(optional.get());
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
        return Response.success("");
    }
}
