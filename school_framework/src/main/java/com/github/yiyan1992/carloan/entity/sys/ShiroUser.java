package com.github.yiyan1992.carloan.entity.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ShiroUser implements Serializable {

    @JsonIgnore
    private String username;

    private String name;

    private String userType;

    @JsonIgnore
    private String type;

    @JsonIgnore
    private Object object;
}
