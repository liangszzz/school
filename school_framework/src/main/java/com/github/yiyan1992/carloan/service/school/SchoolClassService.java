package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolClassDao;
import com.github.yiyan1992.carloan.entity.school.SchoolClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassService {

    @Autowired
    private SchoolClassDao schoolClassDao;



    public void save(SchoolClass schoolClass) {
        schoolClassDao.save(schoolClass);
    }
}
