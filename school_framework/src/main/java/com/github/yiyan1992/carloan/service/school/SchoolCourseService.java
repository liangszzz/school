package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolCourseDao;
import com.github.yiyan1992.carloan.entity.school.SchoolCourse;
import com.github.yiyan1992.carloan.entity.school.SchoolYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
