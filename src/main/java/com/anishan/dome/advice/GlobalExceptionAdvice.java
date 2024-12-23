package com.anishan.dome.advice;

import cn.hutool.http.HttpStatus;
import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public AjaxResponse<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        log.error(String.valueOf(e.getClass()));
        return AjaxResponse.error(HttpStatus.HTTP_INTERNAL_ERROR, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public AjaxResponse<Void> handleRuntimeException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResponse.error(HttpStatus.HTTP_INTERNAL_ERROR, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public AjaxResponse<Void> handleBusinessException(BusinessException e) {
        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public AjaxResponse<Void> handleNoHandlerFoundException(NoHandlerFoundException e) {
        return AjaxResponse.error(HttpStatus.HTTP_NOT_FOUND, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public AjaxResponse<Void> handleBindException(BindException e) {
        StringBuilder msg = new StringBuilder();
        for (FieldError fieldError : e.getFieldErrors()) {

               msg.append(fieldError.getField()).append(" 不能为 ").append(fieldError.getRejectedValue()).append("\n");
        }
        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, msg.toString());
    }





}
