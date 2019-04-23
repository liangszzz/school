package com.github.yiyan1992.carloan.entity.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 */
@Data
public class Page<T> implements Serializable {

    private int size = 10;

    private int start = 0;

    private int page = 0;

    private int total;

    private List<T> list;


}
