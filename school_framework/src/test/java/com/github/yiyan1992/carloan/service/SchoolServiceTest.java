package com.github.yiyan1992.carloan.service;

import com.github.yiyan1992.carloan.entity.school.*;
import com.github.yiyan1992.carloan.service.school.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolServiceTest {

    @Autowired
    private SchoolCourseService schoolCourseService;

    @Autowired
    private SchoolTeacherService schoolTeacherService;

    @Autowired
    private SchoolStudentService schoolStudentService;

    @Autowired
    private SchoolClassService schoolClassService;

    @Autowired
    private SchoolYearService schoolYearService;


    /**
     * test save course
     */
    @Test
    public void addCourse() {
        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setName("2019");

        schoolYear = schoolYearService.save(schoolYear);

        SchoolCourse schoolCourse = new SchoolCourse();
        schoolCourse.setName("大学英语");
        schoolCourse.setHour(40);
        schoolCourse.setScore(5);
        schoolCourse.setSchoolYear(schoolYear);

        schoolCourseService.save(schoolCourse);
    }

    private SchoolYear finYear() {
        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setId(3);
        schoolYear.setName("2019");
        return schoolYear;
    }

    private Set<SchoolCourse> findCourse() {

        List<SchoolCourse> courses = schoolCourseService.findCourseByYear(finYear());
        Set<SchoolCourse> coursesSet = new HashSet<>();
        coursesSet.addAll(courses);
        return coursesSet;
    }

    /**
     * test add teacher
     */
    @Test
    public void addTeacher() {
        SchoolTeacher schoolTeacher = new SchoolTeacher();
        schoolTeacher.setIdCard("320803199111110111");
        schoolTeacher.setName("张三");
        schoolTeacher.setSchoolCourse(findCourse());
        schoolTeacher.setWorkNo("abc");
        schoolTeacherService.save(schoolTeacher);
    }

    /**
     * test add class
     */
    @Test
    public void addClass() {

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setName("信管001");
        schoolClass.setCourses(findCourse());
        schoolClassService.save(schoolClass);
    }

    /**
     * test add student
     */
    @Test
    public void addStudent() {
        SchoolStudent schoolStudent = new SchoolStudent();
        schoolStudent.setIdCard("320830199911110111");
        schoolStudent.setName("王五");
        schoolStudent.setSchoolNo("20190001");
        schoolStudent.setSchoolYear(finYear());
        schoolStudent.setCourses(findCourse());
        schoolStudentService.save(schoolStudent);
    }

    @Test
    public void findTeacherByWorkNo() {
        Optional<SchoolTeacher> abc = schoolTeacherService.findUserByWorkNo("abc");
        if (abc.isPresent()) {
            System.out.println(abc.get().getName());
        }
    }

    @Test
    public void findStudentBySchoolNo() {
        Optional<SchoolStudent> abc = schoolStudentService.findUserBySchoolNo("20190001");
        if (abc.isPresent()) {
            System.out.println(abc.get().getName());
        }
    }

    @Test
    public void findYearList() {
        List<SchoolYear> list = schoolYearService.findList(new SchoolYear());
        System.out.println(list);
    }
}
