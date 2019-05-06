package com.github.yiyan1992.carloan.entity.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author admin
 */
@Data
@NoArgsConstructor
public class Response implements Serializable {

    private int code;

    private Object entity;

    public static Response success(Object obj) {
        return Response.of(200, obj);
    }

    public static Response error(Object obj) {
        return Response.of(500, obj);
    }

    public static Response of(int code, Object t) {
        Response response = new Response();
        response.code = code;
        response.entity = t;
        return response;
    }
}
