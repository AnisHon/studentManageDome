package com.anishan.dome.service.impl;

import com.anishan.dome.domain.LoginUser;
import com.anishan.dome.domain.dto.LoginForm;
import com.anishan.dome.enumeration.LoginErrorEnum;
import com.anishan.dome.exception.AuthException;
import com.anishan.dome.service.AuthService;
import com.anishan.dome.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final AuthUtils authUtils;


    @Override
    public void login(LoginForm loginForm) {

        String token = loginForm.getToken();
        String captcha = loginForm.getCaptcha();

        boolean isRight = authUtils.checkCaptcha(token, captcha);
        if (!isRight) {
            throw new AuthException("验证码错误", LoginErrorEnum.WrongCaptcha);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginForm.getWorkNumber(), loginForm.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);


        if (!(authenticate.getPrincipal() instanceof LoginUser)) {
            throw new AuthException("用户名或密码错误", LoginErrorEnum.WrongPassword);
        }

        LoginUser principal = (LoginUser) authenticate.getPrincipal();

        authUtils.cacheUser(principal);

//        JWTUtil.createToken()




    }
}
