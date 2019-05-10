package com.github.yiyan1992.carloan.config;


import com.github.yiyan1992.carloan.entity.exception.NoFindDataException;
import com.github.yiyan1992.carloan.entity.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionHandles {

    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Response noAuthExceptionHandler(Exception ex) {
        log.info("no auth error", ex);
        return Response.of(300, "no auth");
    }

    @ResponseBody
    @ExceptionHandler(value = NoFindDataException.class)
    public Response notFondException(Exception ex) {
        return Response.error("没有找到此" + ex.getMessage() + "数据");
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Response otherExceptionHandler(Exception ex) {
        log.error("other error", ex);
        return Response.error(ex.getMessage());
    }

}
