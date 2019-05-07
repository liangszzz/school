package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolClassDao;
import com.github.yiyan1992.carloan.dao.school.SchoolCourseDao;
import com.github.yiyan1992.carloan.entity.query.ClassCourse;
import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolClass;
import com.github.yiyan1992.carloan.entity.school.SchoolCourse;
import com.github.yiyan1992.carloan.entity.sys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SchoolClassService {

    @Autowired
    private SchoolClassDao schoolClassDao;

    @Autowired
    private SchoolCourseDao schoolCourseDao;

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

    public Response saveClassCourse(ClassCourse classCourse) {
        Optional<SchoolClass> schoolClass = schoolClassDao.findById(classCourse.getId());
        if (schoolClass.isPresent()) {
            List<SchoolCourse> courses = schoolCourseDao.findAllById(classCourse.getCourses());
            Set<SchoolCourse> set = new HashSet<>();
            set.addAll(courses);
            schoolClass.get().setCourses(set);
            schoolClassDao.save(schoolClass.get());
            return Response.success("保存成功!");
        }
        return Response.error("班级不存在!");
    }
}
