package com.github.yiyan1992.carloan.entity.request;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;

@Data
public abstract class Request<T> {

    @Transient
    private int page;

    @Transient
    private int size = 10;

    public abstract Example<T> getPageExample();

    public PageRequest getPageRequest() {
        return PageRequest.of(page, size);
    }
}
