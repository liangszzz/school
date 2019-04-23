package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolStudentDao;
import com.github.yiyan1992.carloan.entity.school.SchoolStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolStudentService {

    @Autowired
    private SchoolStudentDao schoolStudentDao;


    public void save(SchoolStudent schoolStudent) {
        schoolStudentDao.save(schoolStudent);
    }

    public Optional<SchoolStudent> findUserBySchoolNo(String schoolNo) {
        SchoolStudent schoolStudent = new SchoolStudent();
        schoolStudent.setSchoolNo(schoolNo);
        Example<SchoolStudent> example = Example.of(schoolStudent);
        return schoolStudentDao.findOne(example);
    }
}
