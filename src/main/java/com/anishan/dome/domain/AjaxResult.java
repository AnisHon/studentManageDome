package com.anishan.dome.domain;

import cn.hutool.http.HttpStatus;
import lombok.Data;

@Data
public class AjaxResult<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> AjaxResult<T> ok(T data) {
        AjaxResult<T> tAjaxResult = new AjaxResult<>();
        tAjaxResult.setCode(HttpStatus.HTTP_OK);
        tAjaxResult.setData(data);
        return tAjaxResult;
    }


    public static AjaxResult<Void> error(Integer code, String message) {
        AjaxResult<Void> result = new AjaxResult<Void>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }


}
