package com.anishan.dome.filter;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.LoginUser;
import com.anishan.dome.enumeration.LoginErrorEnum;
import com.anishan.dome.exception.AuthException;
import com.anishan.dome.utils.AuthUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    private final AuthUtils authUtils;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    private void wrong(String msg, HttpServletResponse response) {
        AjaxResponse<Void> error = AjaxResponse.error(HttpStatus.HTTP_UNAUTHORIZED, msg);
        String s = objectMapper.writeValueAsString(error);

        response.setStatus(401);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(s);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        JWT jwt = null;
        try {
            jwt = JWTUtil.parseToken(token);
        } catch (Exception e) {
            wrong( "无效证书", response);
            return;
        }

        NumberWithFormat id = (NumberWithFormat) jwt.getPayload("id");

        if (id == null) {
            wrong( "无效证书", response);
            return;
        }


        LoginUser user = authUtils.getUser(id.longValue());

        if (user == null) {
            wrong("认证已过期", response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());


        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
