package com.github.yiyan1992.carloan.entity.query;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleMenu implements Serializable {

    private String roleName;

    private List<Integer> menus;
}
