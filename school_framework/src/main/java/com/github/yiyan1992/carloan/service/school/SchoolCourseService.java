package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolCourseDao;
import com.github.yiyan1992.carloan.dao.school.SchoolTeacherDao;
import com.github.yiyan1992.carloan.entity.query.CourseTeacher;
import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolCourse;
import com.github.yiyan1992.carloan.entity.school.SchoolTeacher;
import com.github.yiyan1992.carloan.entity.school.SchoolYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SchoolCourseService {

    @Autowired
    private SchoolCourseDao schoolCourseDao;

    @Autowired
    private SchoolTeacherDao schoolTeacherDao;

    public List<SchoolCourse> findCourseByYear(SchoolYear schoolYear) {
        SchoolCourse schoolCourse = new SchoolCourse();
        schoolCourse.setSchoolYear(schoolYear);
        Example<SchoolCourse> example = Example.of(schoolCourse);
        return schoolCourseDao.findAll(example);
    }

    public SchoolCourse save(SchoolCourse schoolCourse) {
        return schoolCourseDao.save(schoolCourse);
    }


    public void deleteById(Integer id) {
        schoolCourseDao.deleteById(id);
    }

    public Optional<SchoolCourse> findById(Integer id) {
        return schoolCourseDao.findById(id);
    }

    public Page<SchoolCourse> findPageList(Example<SchoolCourse> pageExample, PageRequest pageRequest) {
        return schoolCourseDao.findAll(pageExample, pageRequest);
    }

    public List<SchoolCourse> findAllByYear(SchoolYear schoolYear) {
        SchoolCourse course = new SchoolCourse();
        course.setSchoolYear(schoolYear);
        return schoolCourseDao.findAll(course.getExample());
    }

    public Response saveCourseTeacher(CourseTeacher courseTeacher) {
        Optional<SchoolCourse> course = schoolCourseDao.findById(courseTeacher.getId());
        if (course.isPresent()) {
            List<SchoolTeacher> teachers = schoolTeacherDao.findAllById(courseTeacher.getTeachers());
            Set<SchoolTeacher> schoolTeacherSet = new HashSet<>();
            schoolTeacherSet.addAll(teachers);
            course.get().setSchoolTeachers(schoolTeacherSet);
            schoolCourseDao.save(course.get());
            return Response.success("保存成功");
        }
        return Response.error("课程不存在");
    }
}
