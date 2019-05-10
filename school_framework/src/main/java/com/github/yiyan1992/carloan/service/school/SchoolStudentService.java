package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolClassDao;
import com.github.yiyan1992.carloan.dao.school.SchoolStudentDao;
import com.github.yiyan1992.carloan.entity.exception.NoFindDataException;
import com.github.yiyan1992.carloan.entity.school.SchoolClass;
import com.github.yiyan1992.carloan.entity.school.SchoolStudent;
import com.github.yiyan1992.carloan.entity.school.SchoolYear;
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

    @Autowired
    private SchoolClassDao schoolClassDao;

    public SchoolStudent save(SchoolStudent schoolStudent) throws NoFindDataException {
        Optional<SchoolClass> schoolClass = schoolClassDao.findById(schoolStudent.getSchoolClass().getId());
        if (schoolClass.isPresent()) {
            schoolStudent.setSchoolYear(schoolClass.get().getSchoolYear());
            return schoolStudentDao.save(schoolStudent);
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
        schoolStudentDao.deleteById(id);
    }
}
