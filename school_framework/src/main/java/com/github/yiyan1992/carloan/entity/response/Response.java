package com.github.yiyan1992.carloan.entity.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * @author admin
 */
@Data
@NoArgsConstructor
public class Response<T> implements Serializable {

    private int code;

    private String msg;

    private T entity;

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Response SUCCESS() {
        return new Response(200, "success");
    }

    public static Response of(int code, String msg) {
        return new Response(code, msg);
    }

    public static Response of(Page page) {
        Response response = new Response<>(200, "success");
        response.setEntity(page);
        return response;
    }
}
