package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolTeacherDao;
import com.github.yiyan1992.carloan.entity.school.SchoolTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolTeacherService {

    @Autowired
    private SchoolTeacherDao schoolTeacherDao;

    public void save(SchoolTeacher schoolTeacher) {
        schoolTeacherDao.save(schoolTeacher);
    }

    /**
     * 根据教师工号查询
     *
     * @param workNo
     * @return
     */
    public Optional<SchoolTeacher> findUserByWorkNo(String workNo) {
        SchoolTeacher schoolTeacher = new SchoolTeacher();
        schoolTeacher.setWorkNo(workNo);
        Example<SchoolTeacher> example = Example.of(schoolTeacher);
        return schoolTeacherDao.findOne(example);
    }
}
