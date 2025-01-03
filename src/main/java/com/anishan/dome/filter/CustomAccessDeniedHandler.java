package com.anishan.dome.filter;

import cn.hutool.http.HttpStatus;
import com.anishan.dome.domain.AjaxResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor()
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        response.setStatus(HttpStatus.HTTP_FORBIDDEN);
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        AjaxResponse<Void> error = AjaxResponse.error(HttpStatus.HTTP_FORBIDDEN, "拒绝访问");
        objectMapper.writeValue(response.getWriter(), error);

//        log.info("access denied", accessDeniedException);
    }
}
