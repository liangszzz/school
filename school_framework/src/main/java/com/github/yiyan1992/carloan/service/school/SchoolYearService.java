package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolYearDao;
import com.github.yiyan1992.carloan.entity.school.SchoolYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolYearService {

    @Autowired
    private SchoolYearDao schoolYearDao;

    public List<SchoolYear> findList(SchoolYear schoolYear) {
        Example<SchoolYear> example = Example.of(schoolYear);
        return schoolYearDao.findAll(example);
    }

    public SchoolYear save(SchoolYear schoolYear) {
        return schoolYearDao.save(schoolYear);
    }

    public Page<SchoolYear> findPageList(Example<SchoolYear> pageExample, PageRequest pageRequest) {
        return schoolYearDao.findAll(pageExample, pageRequest);
    }

    public Optional<SchoolYear> findById(Integer id) {
        return schoolYearDao.findById(id);
    }

    public void deleteById(Integer id) {
        schoolYearDao.deleteById(id);
    }

    public List<SchoolYear> findAll() {
        return schoolYearDao.findAll();
    }
}
