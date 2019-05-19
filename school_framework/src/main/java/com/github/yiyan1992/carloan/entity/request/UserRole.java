package com.github.yiyan1992.carloan.entity.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserRole implements Serializable {

    private String username;

    private List<String> roles;

}
