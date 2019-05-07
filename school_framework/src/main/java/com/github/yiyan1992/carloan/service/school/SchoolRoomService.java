package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolRoomDao;
import com.github.yiyan1992.carloan.entity.school.SchoolRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolRoomService {

    @Autowired
    private SchoolRoomDao schoolRoomDao;

    public SchoolRoom save(SchoolRoom schoolRoom) {
        return schoolRoomDao.save(schoolRoom);
    }

    public Page<SchoolRoom> findPageList(Example<SchoolRoom> pageExample, PageRequest pageRequest) {
        return schoolRoomDao.findAll(pageExample, pageRequest);
    }

    public Optional<SchoolRoom> findById(Integer id) {
        return schoolRoomDao.findById(id);
    }

    public void deleteById(Integer id) {
        schoolRoomDao.deleteById(id);
    }

    public List<SchoolRoom> findAll() {
        return schoolRoomDao.findAll();
    }
}
