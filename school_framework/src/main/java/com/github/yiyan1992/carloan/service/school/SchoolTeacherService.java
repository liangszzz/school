package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.*;
import com.github.yiyan1992.carloan.entity.exception.NoFindDataException;
import com.github.yiyan1992.carloan.entity.base.Response;
import com.github.yiyan1992.carloan.entity.response.StudentCourseResponse;
import com.github.yiyan1992.carloan.entity.school.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolTeacherService {

    @Autowired
    private SchoolTeacherDao schoolTeacherDao;

    @Autowired
    private SchoolCourseDao schoolCourseDao;

    @Autowired
    private SchoolClassDao schoolClassDao;

    @Autowired
    private SchoolStudentDao schoolStudentDao;

    @Autowired
    private SchoolClassCourseTeacherDao schoolClassCourseTeacherDao;

    @Autowired
    private SchoolStudentCourseTeacherDao schoolStudentCourseTeacherDao;

    public SchoolTeacher save(SchoolTeacher schoolTeacher) {
        if (schoolTeacher.getId() == null) {
            schoolTeacher.setPassword(schoolTeacher.getIdCard());
        } else {
            Optional<SchoolTeacher> teacher = schoolTeacherDao.findById(schoolTeacher.getId());
            if (teacher.isEmpty()) throw new NoFindDataException("教师");
            schoolTeacher.setPassword(teacher.get().getPassword());
        }
        return schoolTeacherDao.save(schoolTeacher);
    }

    public Optional<SchoolTeacher> findUserByWorkNo(String workNo) {
        SchoolTeacher schoolTeacher = new SchoolTeacher();
        schoolTeacher.setWorkNo(workNo);
        Example<SchoolTeacher> example = Example.of(schoolTeacher);
        return schoolTeacherDao.findOne(example);
    }

    public Page<SchoolTeacher> findPageList(Example<SchoolTeacher> pageExample, PageRequest pageRequest) {
        return schoolTeacherDao.findAll(pageExample, pageRequest);
    }

    public Optional<SchoolTeacher> findById(Integer id) {
        return schoolTeacherDao.findById(id);
    }

    public void deleteById(Integer id) {
        Optional<SchoolTeacher> teacher = schoolTeacherDao.findById(id);
        if (teacher.isEmpty()) throw new NoFindDataException("教师");
        //删除班级课程老师
        SchoolClassCourseTeacher schoolClassCourseTeacher = new SchoolClassCourseTeacher();
        schoolClassCourseTeacher.setSchoolTeacher(teacher.get());
        List<SchoolClassCourseTeacher> schoolClassCourseTeachers = schoolClassCourseTeacherDao.findAll(Example.of(schoolClassCourseTeacher));
        schoolClassCourseTeacherDao.deleteAll(schoolClassCourseTeachers);
        //获取学生课程老师
        SchoolStudentCourseTeacher schoolStudentCourseTeacher = new SchoolStudentCourseTeacher();
        schoolStudentCourseTeacher.setSchoolTeacher(teacher.get());
        List<SchoolStudentCourseTeacher> schoolStudentCourseTeachers = schoolStudentCourseTeacherDao.findAll(Example.of(schoolStudentCourseTeacher));
        schoolStudentCourseTeacherDao.deleteAll(schoolStudentCourseTeachers);
        //删除老师
        schoolTeacherDao.deleteById(id);
    }

    public List<SchoolTeacher> findAll() {
        return schoolTeacherDao.findAll();
    }

    public List<SchoolTeacher> findAllByCourse(Integer courseId) {
        Optional<SchoolCourse> course = schoolCourseDao.findById(courseId);
        return course.map(schoolCourse -> new ArrayList<>(schoolCourse.getSchoolTeachers())).orElse(null);
    }

    public Response findByCourseAndClass(Integer courseId, Integer classId) {
        Optional<SchoolCourse> schoolCourse = schoolCourseDao.findById(courseId);
        Optional<SchoolClass> schoolClass = schoolClassDao.findById(classId);
        if (schoolCourse.isPresent() && schoolClass.isPresent()) {
            SchoolClassCourseTeacher schoolClassCourseTeacher = new SchoolClassCourseTeacher();
            schoolClassCourseTeacher.setSchoolCourse(schoolCourse.get());
            schoolClassCourseTeacher.setSchoolClass(schoolClass.get());
            Example<SchoolClassCourseTeacher> example = Example.of(schoolClassCourseTeacher);
            Optional<SchoolClassCourseTeacher> teacher = schoolClassCourseTeacherDao.findOne(example);
            if (teacher.isPresent()) {
                return Response.success(teacher.get().getSchoolTeacher());
            }
            return Response.error("没有老师!");
        }
        return Response.error("课程或班级不存在!");
    }

    public List<SchoolCourse> findAllCourseByWorkNo(String workNo) {
        return schoolCourseDao.findAllByWorkNo(workNo);
    }

    public List<StudentCourseResponse> findStudentCourseByWorkNo(String workNo, Integer courseId) {
        Optional<SchoolTeacher> schoolTeacher = findUserByWorkNo(workNo);
        if (schoolTeacher.isEmpty()) throw new NoFindDataException("教师");
        Optional<SchoolCourse> schoolCourse = schoolCourseDao.findById(courseId);
        if (schoolCourse.isEmpty()) throw new NoFindDataException("课程");

        SchoolStudentCourseTeacher schoolStudentCourseTeacher = new SchoolStudentCourseTeacher();
        schoolStudentCourseTeacher.setSchoolTeacher(schoolTeacher.get());
        schoolStudentCourseTeacher.setSchoolCourse(schoolCourse.get());

        List<SchoolStudentCourseTeacher> all = schoolStudentCourseTeacherDao.findAll(Example.of(schoolStudentCourseTeacher));
        List<StudentCourseResponse> list = new ArrayList<>();
        all.stream().forEach(e -> {
            StudentCourseResponse studentCourseResponse = new StudentCourseResponse();
            studentCourseResponse.setId(e.getId());
            studentCourseResponse.setCourseId(e.getSchoolCourse().getId());
            studentCourseResponse.setCourseName(e.getSchoolCourse().getName());
            if (e.getScore() != null)
                studentCourseResponse.setScore(e.getScore());
            studentCourseResponse.setTeacherName(e.getSchoolTeacher().getName());
            studentCourseResponse.setTeacherId(e.getSchoolTeacher().getId());
            studentCourseResponse.setStudentId(e.getSchoolStudent().getId());
            studentCourseResponse.setStudentName(e.getSchoolStudent().getName());
            studentCourseResponse.setClassId(e.getSchoolStudent().getSchoolClass().getId());
            studentCourseResponse.setClassName(e.getSchoolStudent().getSchoolClass().getName());

            list.add(studentCourseResponse);
        });
        return list;
    }

    public void scoreToStudent(String workNo, Integer courseId, Integer studentId, Integer score) {
        Optional<SchoolTeacher> schoolTeacher = findUserByWorkNo(workNo);
        if (schoolTeacher.isEmpty()) throw new NoFindDataException("教师");
        Optional<SchoolCourse> schoolCourse = schoolCourseDao.findById(courseId);
        if (schoolCourse.isEmpty()) throw new NoFindDataException("课程");
        Optional<SchoolStudent> schoolStudent = schoolStudentDao.findById(studentId);
        if (schoolStudent.isEmpty()) throw new NoFindDataException("学生");

        SchoolStudentCourseTeacher schoolStudentCourseTeacher = new SchoolStudentCourseTeacher();
        schoolStudentCourseTeacher.setSchoolTeacher(schoolTeacher.get());
        schoolStudentCourseTeacher.setSchoolCourse(schoolCourse.get());
        schoolStudentCourseTeacher.setSchoolStudent(schoolStudent.get());

        Optional<SchoolStudentCourseTeacher> one = schoolStudentCourseTeacherDao.findOne(Example.of(schoolStudentCourseTeacher));
        if (one.isEmpty()) throw new NoFindDataException("学生课程老师信息");

        one.get().setScore(score);
        schoolStudentCourseTeacherDao.save(one.get());

    }
}
