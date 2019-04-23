package com.github.yiyan1992.carloan.entity.query;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Query2 implements Serializable {

    private String id;

    private List<Login> logins = new ArrayList<>();
}
