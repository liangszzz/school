package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolYearDao;
import com.github.yiyan1992.carloan.entity.school.SchoolYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
