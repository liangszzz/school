package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolTeacherDao;
import com.github.yiyan1992.carloan.entity.school.SchoolTeacher;
import com.github.yiyan1992.carloan.entity.school.SchoolYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolTeacherService {

    @Autowired
    private SchoolTeacherDao schoolTeacherDao;

    public SchoolTeacher save(SchoolTeacher schoolTeacher) {
        return schoolTeacherDao.save(schoolTeacher);
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

    public Page<SchoolTeacher> findPageList(Example<SchoolTeacher> pageExample, PageRequest pageRequest) {
        return schoolTeacherDao.findAll(pageExample, pageRequest);
    }

    public Optional<SchoolTeacher> findById(Integer id) {
        return schoolTeacherDao.findById(id);
    }

    public void deleteById(Integer id) {
        schoolTeacherDao.deleteById(id);
    }

    public List<SchoolTeacher> findAll() {
        return schoolTeacherDao.findAll();
    }

}
