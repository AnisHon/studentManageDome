package com.anishan.dome.domain.vo;

import lombok.Data;

@Data
public class LoginResponse {

    private Boolean success;
    private String message;
    private String token;

    public static LoginResponse build(Boolean success, String message, String token) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.success = success;
        loginResponse.message = message;
        loginResponse.token = token;
        return loginResponse;
    }
}
