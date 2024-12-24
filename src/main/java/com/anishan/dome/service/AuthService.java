package com.anishan.dome.service;

import com.anishan.dome.domain.dto.LoginForm;

public interface AuthService {
    String login(LoginForm loginForm);
}
