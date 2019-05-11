package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolClassCourseTeacherDao;
import com.github.yiyan1992.carloan.dao.school.SchoolClassDao;
import com.github.yiyan1992.carloan.dao.school.SchoolStudentCourseTeacherDao;
import com.github.yiyan1992.carloan.dao.school.SchoolStudentDao;
import com.github.yiyan1992.carloan.entity.exception.NoFindDataException;
import com.github.yiyan1992.carloan.entity.school.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolStudentService {

    @Autowired
    private SchoolStudentDao schoolStudentDao;

    @Autowired
    private SchoolClassDao schoolClassDao;

    @Autowired
    private SchoolStudentCourseTeacherDao schoolStudentCourseTeacherDao;

    @Autowired
    private SchoolClassCourseTeacherDao schoolClassCourseTeacherDao;

    public SchoolStudent save(SchoolStudent schoolStudent) {
        Optional<SchoolClass> schoolClass = schoolClassDao.findById(schoolStudent.getSchoolClass().getId());
        if (schoolClass.isPresent()) {
            schoolStudent.setSchoolYear(schoolClass.get().getSchoolYear());
            if (schoolStudent.getId() != null) {
                //保存学生老师课程信息
                SchoolStudentCourseTeacher schoolStudentCourseTeacher = new SchoolStudentCourseTeacher();
                schoolStudentCourseTeacher.setSchoolStudent(schoolStudent);
                Example<SchoolStudentCourseTeacher> example = Example.of(schoolStudentCourseTeacher);
                List<SchoolStudentCourseTeacher> list = schoolStudentCourseTeacherDao.findAll(example);
                schoolStudentCourseTeacherDao.deleteAll(list);
            }
            if (schoolStudent.getId() == null) {
                schoolStudent.setPassword(schoolStudent.getIdCard());
            } else {
                Optional<SchoolStudent> student = schoolStudentDao.findById(schoolStudent.getId());
                if (student.isEmpty()) throw new NoFindDataException("学生");
                schoolStudent.setPassword(student.get().getPassword());
            }

            schoolStudent = schoolStudentDao.save(schoolStudent);

            List<SchoolStudentCourseTeacher> savelist = new ArrayList<>();
            for (SchoolCourse next : schoolClass.get().getCourses()) {
                SchoolStudentCourseTeacher tmp = new SchoolStudentCourseTeacher();
                tmp.setSchoolStudent(schoolStudent);
                tmp.setSchoolCourse(next);
                SchoolClassCourseTeacher schoolClassCourseTeacher = new SchoolClassCourseTeacher();
                schoolClassCourseTeacher.setSchoolClass(schoolClass.get());
                schoolClassCourseTeacher.setSchoolCourse(next);
                Optional<SchoolClassCourseTeacher> one = schoolClassCourseTeacherDao.findOne(Example.of(schoolClassCourseTeacher));
                if (one.isEmpty()) throw new NoFindDataException("班级老师课程");
                tmp.setSchoolTeacher(one.get().getSchoolTeacher());
                savelist.add(tmp);
            }
            schoolStudentCourseTeacherDao.saveAll(savelist);
            return schoolStudent;
        }
        throw new NoFindDataException("班级");
    }


    public Optional<SchoolStudent> findUserBySchoolNo(String schoolNo) {
        SchoolStudent schoolStudent = new SchoolStudent();
        schoolStudent.setSchoolNo(schoolNo);
        Example<SchoolStudent> example = Example.of(schoolStudent);
        return schoolStudentDao.findOne(example);
    }

    public Page<SchoolStudent> findPageList(Example<SchoolStudent> pageExample, PageRequest pageRequest) {
        return schoolStudentDao.findAll(pageExample, pageRequest);
    }

    public Optional<SchoolStudent> findById(Integer id) {
        return schoolStudentDao.findById(id);
    }

    public void deleteById(Integer id) {
        Optional<SchoolStudent> schoolStudent = schoolStudentDao.findById(id);
        if (schoolStudent.isEmpty()) {
            throw new NoFindDataException("学生");
        }
        SchoolStudentCourseTeacher schoolStudentCourseTeacher = new SchoolStudentCourseTeacher();
        schoolStudentCourseTeacher.setSchoolStudent(schoolStudent.get());
        List<SchoolStudentCourseTeacher> all = schoolStudentCourseTeacherDao.findAll(Example.of(schoolStudentCourseTeacher));
        schoolStudentCourseTeacherDao.deleteAll(all);
        schoolStudentDao.deleteById(id);
    }
}
