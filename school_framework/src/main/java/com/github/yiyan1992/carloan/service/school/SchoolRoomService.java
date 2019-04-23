package com.github.yiyan1992.carloan.service.school;

import com.github.yiyan1992.carloan.dao.school.SchoolRoomDao;
import com.github.yiyan1992.carloan.entity.school.SchoolRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolRoomService {

    @Autowired
    private SchoolRoomDao schoolRoomDao;

    public void save(SchoolRoom schoolRoom) {
        schoolRoomDao.save(schoolRoom);
    }
}
