package com.anishan.dome.advice;

import cn.hutool.http.HttpStatus;
import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public AjaxResponse<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
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



}
