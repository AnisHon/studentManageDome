package com.anishan.dome.exception;

import com.anishan.dome.enumeration.LoginErrorEnum;
import lombok.Getter;

@Getter
public class AuthException extends BusinessException {


    private final LoginErrorEnum loginErrorEnum;

    public AuthException(String message, LoginErrorEnum loginErrorEnum) {
        super(message);
        this.loginErrorEnum = loginErrorEnum;
    }

}
