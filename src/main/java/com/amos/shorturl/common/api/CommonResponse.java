package com.amos.shorturl.common.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 模块名称: hibernate
 * 模块描述: response
 *
 * @author amos.wang
 * @date 11/20/2020 3:13 PM
 */
@Getter
@Setter
@Accessors(chain = true)
public class CommonResponse<T> {

    public static final CommonResponse<String> SUCCESS = new CommonResponse<String>().setCode("200").setMessage("成功");
    public static final CommonResponse<String> FAIL = new CommonResponse<String>().setCode("400").setMessage("失败");
    public static final CommonResponse<String> ERROR_PARAM = new CommonResponse<String>().setCode("401").setMessage("参数错误");

    private String code;

    private String message;

    private T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<T>().setCode(SUCCESS.getCode()).setMessage(SUCCESS.getMessage()).setData(data);
    }

    public static <T> CommonResponse<T> fail() {
        return new CommonResponse<T>().setCode(FAIL.getCode()).setMessage(FAIL.getMessage());
    }

    public static <T> CommonResponse<T> fail(String message) {
        return new CommonResponse<T>().setCode(FAIL.getCode()).setMessage(message);
    }

    public static <T> CommonResponse<T> fail(String code, String message) {
        return new CommonResponse<T>().setCode(code).setMessage(message);
    }

    public static <T> CommonResponse<T> errorParam(String message) {
        return new CommonResponse<T>().setCode(ERROR_PARAM.getCode()).setMessage(message);
    }

    public boolean isSuccess() {
        return SUCCESS.getCode().equals(getCode());
    }

}
