package com.github.yiyan1992.carloan.controller.school;

import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.school.SchoolRoom;
import com.github.yiyan1992.carloan.service.school.SchoolRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private SchoolRoomService schoolRoomService;


    @PostMapping("/list")
    public Response list(SchoolRoom schoolRoom) {
        Page<SchoolRoom> list = schoolRoomService.findPageList(schoolRoom.getPageExample(), schoolRoom.getPageRequest());
        return Response.of(200, list);
    }

    @PostMapping("/findById/{id}")
    public Response findById(@PathVariable Integer id) {
        Optional<SchoolRoom> optional = schoolRoomService.findById(id);
        return Response.SUCCESS(optional.get());
    }

    @PostMapping("/add")
    public Response add(SchoolRoom schoolRoom) {
        return Response.of(200, schoolRoomService.save(schoolRoom));
    }

    @PostMapping("/update")
    public Response update(SchoolRoom schoolRoom) {
        return Response.of(200, schoolRoomService.save(schoolRoom));
    }

    @PostMapping("/deleteById/{id}")
    public Response delete(@PathVariable Integer id) {
        schoolRoomService.deleteById(id);
        return Response.SUCCESS("");
    }
}
