package com.anishan.dome.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.LoginUser;
import com.anishan.dome.domain.MapAjaxResponse;
import com.anishan.dome.domain.dto.LoginForm;
import com.anishan.dome.domain.dto.StatusForm;
import com.anishan.dome.domain.entity.SysUser;
import com.anishan.dome.domain.vo.CaptchaResponse;
import com.anishan.dome.domain.vo.LoginResponse;
import com.anishan.dome.exception.AuthException;
import com.anishan.dome.service.AuthService;
import com.anishan.dome.utils.AuthUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthUtils authUtils;
    private final AuthService authService;



    @ResponseBody
    @PostMapping("/login")
    @ApiOperation("登录接口，需要验证码 验证码token")
    public AjaxResponse<LoginResponse> login(@RequestBody LoginForm loginForm) {
        String token = null;
        try {
            token = authService.login(loginForm);

        } catch (AuthException | AuthenticationException e) {
            return AjaxResponse.ok(LoginResponse.build(false, e.getMessage(), token));
        }
        return AjaxResponse.ok(LoginResponse.build(true, null, token));
    }


    @ResponseBody
    @GetMapping("/logout")
    @ApiOperation("退出登录接口")
    public AjaxResponse<Void> logout() {
        authUtils.logout();
        return AjaxResponse.ok(null);
    }

    @ResponseBody
    @GetMapping("/captcha")
    @ApiOperation("获取验证码")
    public AjaxResponse<CaptchaResponse> getCurrentUser() {

        CaptchaResponse captcha = authUtils.getCaptcha();
        return AjaxResponse.ok(captcha);
    }

    @ResponseBody
    @GetMapping
    @ApiOperation("获取自己")
    public AjaxResponse<LoginUser> myself() {
        return AjaxResponse.ok(AuthUtils.getLoginUser());
    }



}
