package com.amos.shorturl.web.exception;

import com.amos.common.dto.response.SingleResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * DESCRIPTION: 统一异常拦截
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/12/4
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * Controller统一参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SingleResponse<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);

        return SingleResponse.ofErrorParam(objectError.getDefaultMessage());
    }

}
