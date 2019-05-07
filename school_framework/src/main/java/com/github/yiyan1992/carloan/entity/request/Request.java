package com.github.yiyan1992.carloan.entity.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;

@Data
public abstract class Request<T> {

    @Transient
    @JsonIgnore
    private int page;

    @Transient
    @JsonIgnore
    private int size = 10;

    @JsonIgnore
    public abstract Example<T> getExample();

    @JsonIgnore
    public PageRequest getPageRequest() {
        return PageRequest.of(page, size);
    }
}
