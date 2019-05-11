package com.github.yiyan1992.carloan.entity.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class StudentCourseResponse implements Serializable {

    private int id;

    private Integer courseId;

    private String courseName;

    private Integer teacherId;

    private String teacherName;

    private Integer studentId;

    private String studentName;

    private Integer classId;

    private String className;

    private int score;

}
