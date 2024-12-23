package com.anishan.dome.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.LoginUser;
import com.anishan.dome.domain.MapAjaxResponse;
import com.anishan.dome.domain.dto.LoginForm;
import com.anishan.dome.domain.dto.SignUpForm;
import com.anishan.dome.domain.vo.CaptchaResponse;
import com.anishan.dome.service.AuthService;
import com.anishan.dome.utils.AuthUtils;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthUtils authUtils;
    private final AuthService authService;



    @ResponseBody
    @PostMapping("/login")
    @ApiOperation("登录接口，需要验证码 验证码token")
    public AjaxResponse<Void> login(@RequestBody LoginForm loginForm) {
        authService.login(loginForm);
        return AjaxResponse.ok(null);
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
    @GetMapping()
    @ApiOperation("获取自己")
    public AjaxResponse<LoginUser> myself() {
        return AjaxResponse.ok(AuthUtils.getLoginUser());
    }

}
