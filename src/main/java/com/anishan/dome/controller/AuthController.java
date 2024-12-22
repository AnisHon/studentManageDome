package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.LoginForm;
import com.anishan.dome.domain.dto.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {



    @ResponseBody
    @PostMapping("/login")
    public AjaxResponse<Void> login(@RequestBody LoginForm loginForm) {
        return AjaxResponse.ok(null);
    }

    @ResponseBody
    @PostMapping("/sign-up")
    public AjaxResponse<Void> signUp(@RequestBody SignUpForm signUpForm) {
        return AjaxResponse.ok(null);
    }

    @ResponseBody
    @GetMapping("/logout")
    public AjaxResponse<Void> logout() {
        return AjaxResponse.ok(null);
    }

}
