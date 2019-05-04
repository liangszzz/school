package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolClassDao;
import com.github.yiyan1992.carloan.entity.school.SchoolClass;
import com.github.yiyan1992.carloan.entity.sys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolClassService {

    @Autowired
    private SchoolClassDao schoolClassDao;

    public SchoolClass save(SchoolClass schoolClass) {
        return schoolClassDao.save(schoolClass);
    }

    public Page<SchoolClass> findPageList(Example<SchoolClass> pageExample, PageRequest pageRequest) {
        return schoolClassDao.findAll(pageExample, pageRequest);
    }

    public Optional<SchoolClass> findById(int id) {
        return schoolClassDao.findById(id);
    }

    public void deleteById(Integer id) {
        schoolClassDao.deleteById(id);
    }
}
