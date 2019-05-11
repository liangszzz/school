package com.github.yiyan1992.carloan.dao.school;

import com.github.yiyan1992.carloan.entity.school.SchoolCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SchoolCourseDao extends JpaRepository<SchoolCourse, Integer> {


    @Query(nativeQuery = true, value = "SELECT course.* FROM school_course course" +
            " INNER JOIN school_course_teacher course_teacher ON course_teacher.course_id=course.id" +
            " INNER JOIN school_teacher teacher on teacher.id=course_teacher.teacher_id" +
            " where teacher.work_no=:workNo")
    List<SchoolCourse> findAllByWorkNo(String workNo);
}
