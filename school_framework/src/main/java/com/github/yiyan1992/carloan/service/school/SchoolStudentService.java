package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolStudentDao;
import com.github.yiyan1992.carloan.entity.school.SchoolStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolStudentService {

    @Autowired
    private SchoolStudentDao schoolStudentDao;


    public SchoolStudent save(SchoolStudent schoolStudent) {
        return schoolStudentDao.save(schoolStudent);
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
        schoolStudentDao.findById(id);
    }
}
