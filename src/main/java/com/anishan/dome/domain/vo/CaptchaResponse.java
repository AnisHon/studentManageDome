package com.anishan.dome.domain.vo;

import lombok.Data;

@Data
public class CaptchaResponse {
    private String token;
    private String img;

}
