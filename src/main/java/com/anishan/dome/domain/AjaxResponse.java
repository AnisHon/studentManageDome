package com.anishan.dome.domain;

import cn.hutool.http.HttpStatus;
import lombok.Data;

@Data
public class AjaxResponse<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> AjaxResponse<T> ok(T data) {
        AjaxResponse<T> tAjaxResponse = new AjaxResponse<>();
        tAjaxResponse.setCode(HttpStatus.HTTP_OK);
        tAjaxResponse.setData(data);
        return tAjaxResponse;
    }


    public static AjaxResponse<Void> error(Integer code, String message) {
        AjaxResponse<Void> result = new AjaxResponse<Void>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }


}
