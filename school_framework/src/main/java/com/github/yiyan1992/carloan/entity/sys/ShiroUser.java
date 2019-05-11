package com.github.yiyan1992.carloan.entity.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ShiroUser implements Serializable {

    private String name;

    private String userType;

    @JsonIgnore
    private Object object;
}
