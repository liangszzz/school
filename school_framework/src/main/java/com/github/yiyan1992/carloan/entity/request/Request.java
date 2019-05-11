package com.github.yiyan1992.carloan.entity.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
public abstract class Request<T> {

    @Transient
    @JsonIgnore
    private int page = 0;

    @Transient
    @JsonIgnore
    private int size = 10;

    @JsonIgnore
    public abstract Example<T> getExample();

    @JsonIgnore
    public PageRequest getPageRequest() {
        return PageRequest.of(getPage(), size);
    }

    public int getPage() {
        if (page > 0) return page - 1;
        return page;
    }
}
