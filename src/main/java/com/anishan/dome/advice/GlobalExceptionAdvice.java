package com.anishan.dome.advice;

import cn.hutool.http.HttpStatus;
import com.anishan.dome.domain.AjaxResult;
import com.anishan.dome.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public AjaxResult<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(HttpStatus.HTTP_INTERNAL_ERROR, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult<Void> handleRuntimeException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(HttpStatus.HTTP_INTERNAL_ERROR, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public AjaxResult<Void> handleBusinessException(BusinessException e) {
        return AjaxResult.error(HttpStatus.HTTP_BAD_REQUEST, e.getMessage());
    }



}
