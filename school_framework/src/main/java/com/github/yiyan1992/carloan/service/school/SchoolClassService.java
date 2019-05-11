package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolClassCourseTeacherDao;
import com.github.yiyan1992.carloan.dao.school.SchoolClassDao;
import com.github.yiyan1992.carloan.dao.school.SchoolCourseDao;
import com.github.yiyan1992.carloan.dao.school.SchoolTeacherDao;
import com.github.yiyan1992.carloan.entity.request.ClassCourse;
import com.github.yiyan1992.carloan.entity.base.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolClass;
import com.github.yiyan1992.carloan.entity.school.SchoolClassCourseTeacher;
import com.github.yiyan1992.carloan.entity.school.SchoolCourse;
import com.github.yiyan1992.carloan.entity.school.SchoolTeacher;
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
public class SchoolClassService {

    @Autowired
    private SchoolClassDao schoolClassDao;

    @Autowired
    private SchoolCourseDao schoolCourseDao;

    @Autowired
    private SchoolTeacherDao schoolTeacherDao;

    @Autowired
    private SchoolClassCourseTeacherDao schoolClassCourseTeacherDao;

    public SchoolClass save(SchoolClass schoolClass) {
        return schoolClassDao.save(schoolClass);
    }

    public Page<SchoolClass> findPageList(Example<SchoolClass> pageExample, PageRequest pageRequest) {
        return schoolClassDao.findAll(pageExample, pageRequest);
    }

    public Optional<SchoolClass> findById(int id) {
        return schoolClassDao.findById(id);
    }

    public void deleteById(Integer id) {

        schoolClassDao.deleteById(id);
    }

    public Response saveClassCourse(ClassCourse classCourse) {
        Optional<SchoolClass> schoolClass = schoolClassDao.findById(classCourse.getId());
        if (schoolClass.isPresent()) {
            List<SchoolCourse> courses = schoolCourseDao.findAllById(classCourse.getCourses());
            Set<SchoolCourse> set = new HashSet<>(courses);
            schoolClass.get().setCourses(set);
            schoolClassDao.save(schoolClass.get());
            return Response.success("保存成功!");
        }
        return Response.error("班级不存在!");
    }

    public Response saveClassCourseTeacher(Integer courseId, Integer classId, Integer teacherId) {
        Optional<SchoolClass> schoolClass = schoolClassDao.findById(classId);
        Optional<SchoolCourse> schoolCourse = schoolCourseDao.findById(courseId);

        if (schoolClass.isPresent() && schoolCourse.isPresent()) {
            SchoolClassCourseTeacher schoolClassCourseTeacher = new SchoolClassCourseTeacher();
            schoolClassCourseTeacher.setSchoolClass(schoolClass.get());
            schoolClassCourseTeacher.setSchoolCourse(schoolCourse.get());
            Optional<SchoolClassCourseTeacher> one = schoolClassCourseTeacherDao.findOne(Example.of(schoolClassCourseTeacher));

            if (teacherId == null) {
                one.ifPresent(classCourseTeacher -> schoolClassCourseTeacherDao.delete(classCourseTeacher));
                return Response.success("成功!");
            } else {
                Optional<SchoolTeacher> schoolTeacher = schoolTeacherDao.findById(teacherId);
                if (schoolTeacher.isPresent()) {
                    if (one.isPresent()) {
                        one.get().setSchoolTeacher(schoolTeacher.get());
                        schoolClassCourseTeacherDao.save(one.get());
                    } else {
                        schoolClassCourseTeacher.setSchoolTeacher(schoolTeacher.get());
                        schoolClassCourseTeacherDao.save(schoolClassCourseTeacher);
                    }
                } else {
                    one.ifPresent(classCourseTeacher -> schoolClassCourseTeacherDao.delete(classCourseTeacher));
                }
                return Response.success("成功!");
            }
        }
        return Response.error("没有班级和课程!");
    }

    public List<SchoolClass> findAll(SchoolClass schoolClass) {
        return schoolClassDao.findAll(schoolClass.getExample());
    }
}
