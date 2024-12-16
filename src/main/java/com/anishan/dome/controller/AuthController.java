package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResult;
import com.anishan.dome.domain.dto.LoginForm;
import com.anishan.dome.domain.dto.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {



    @ResponseBody
    @PostMapping("/login")
    public AjaxResult<Void> login(@RequestBody LoginForm loginForm) {
        return AjaxResult.ok(null);
    }

    @ResponseBody
    @PostMapping("/sign-up")
    public AjaxResult<Void> signUp(@RequestBody SignUpForm signUpForm) {
        return AjaxResult.ok(null);
    }

    @ResponseBody
    @GetMapping("/logout")
    public AjaxResult<Void> logout() {
        return AjaxResult.ok(null);
    }

}
