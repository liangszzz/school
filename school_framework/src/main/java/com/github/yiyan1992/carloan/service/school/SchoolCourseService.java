package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolCourseDao;
import com.github.yiyan1992.carloan.entity.school.SchoolCourse;
import com.github.yiyan1992.carloan.entity.school.SchoolYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolCourseService {

    @Autowired
    private SchoolCourseDao schoolCourseDao;

    public List<SchoolCourse> findCourseByYear(SchoolYear schoolYear) {
        SchoolCourse schoolCourse = new SchoolCourse();
        schoolCourse.setSchoolYear(schoolYear);
        Example<SchoolCourse> example = Example.of(schoolCourse);
        return schoolCourseDao.findAll(example);
    }

    public void save(SchoolCourse schoolCourse) {
        schoolCourseDao.save(schoolCourse);
    }


}
