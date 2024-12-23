package com.anishan.dome.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.anishan.dome.domain.LoginUser;
import com.anishan.dome.enumeration.LoginErrorEnum;
import com.anishan.dome.exception.AuthException;
import com.anishan.dome.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter implements Filter {

    private final AuthenticationManager authenticationManager;

    private final AuthUtils authUtils;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        JWT jwt = null;
        try {
            jwt = JWTUtil.parseToken(token);
        } catch (Exception e) {
            throw new AuthException("认证无效", LoginErrorEnum.IllegalState);
        }


        Long id = (Long) jwt.getPayload("id");

        LoginUser user = authUtils.getUser(id);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        authenticationManager.authenticate(authenticationToken);


    }
}
