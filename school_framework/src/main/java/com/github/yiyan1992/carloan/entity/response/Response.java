package com.github.yiyan1992.carloan.entity.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author admin
 */
@Data
@NoArgsConstructor
public class Response<T> implements Serializable {

    private int code;

    private String msg;

    private T entity;

    private Page<T> page;

    private HashMap<String, Object> others;

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
}
