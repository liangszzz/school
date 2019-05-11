package com.github.yiyan1992.carloan.entity.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ClassCourse implements Serializable {

    private Integer id;

    private List<Integer> courses;
}
