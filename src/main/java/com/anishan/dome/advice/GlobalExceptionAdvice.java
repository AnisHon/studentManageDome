package com.anishan.dome.advice;

import cn.hutool.http.HttpStatus;
import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.enumeration.LoginErrorEnum;
import com.anishan.dome.exception.AuthException;
import com.anishan.dome.exception.BusinessException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public AjaxResponse<Void> handleRuntimeException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResponse.error(HttpStatus.HTTP_INTERNAL_ERROR, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public AjaxResponse<Void> handleBusinessException(BusinessException e) {
        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(AuthException.class)
    public AjaxResponse<Void> handleAuthException(AuthException e, HttpServletResponse response) {
        int respCode = 0;
        switch (e.getLoginErrorEnum()) {
            case IllegalState:
                respCode = HttpStatus.HTTP_UNAUTHORIZED;
                break;
            case WrongCaptcha:
            case WrongPassword:
            case IllegalPassword:
            case UnknownUsername:
            case UsernameConflict:
                respCode = HttpStatus.HTTP_BAD_REQUEST;
        }

        response.setStatus(respCode);
        return AjaxResponse.error(respCode, e.getMessage());
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

    @ResponseBody
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public AjaxResponse<Void> handleInvalidFormatException(InvalidFormatException e) {
        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, e.getLocation().toString() + "错误");
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public AjaxResponse<Void> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.info("{}{}", e.getClass(), e.getMessage());
        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, "未知参数");
    }


    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public AjaxResponse<Void> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.info(e.getMessage(), e);
        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, "数据冲突，参照完整性错误");
//        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public AjaxResponse<Void> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String collect = String.join(",", Objects.requireNonNull(e.getSupportedMethods()));
        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, "不支持的请求方法：" + e.getMethod() + ",请使用："+ collect);
    }

    @ResponseBody
    @ExceptionHandler(OptimisticLockingFailureException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public AjaxResponse<Void> handleOptimisticLockingFailureException(OptimisticLockingFailureException e) {
        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, "更改失败，请重试");
    }

    @ResponseBody
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public AjaxResponse<Void> handleDuplicateKeyException(DuplicateKeyException e) {
        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, "操作失败，请检查是否重复");
    }

    @ResponseBody
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public AjaxResponse<Void> handleSQLException(SQLException e) {
        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(UncategorizedSQLException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public AjaxResponse<Void> handleUncategorizedSQLException(UncategorizedSQLException e) {
        String message = Objects.requireNonNull(e.getRootCause()).getMessage();
        return AjaxResponse.error(HttpStatus.HTTP_BAD_REQUEST, message);
    }
}
