package com.anishan.dome.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.anishan.dome.domain.LoginUser;
import com.anishan.dome.domain.vo.CaptchaResponse;
import com.anishan.dome.enumeration.LoginErrorEnum;
import com.anishan.dome.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor()
public class AuthUtils {

    private final RedisTemplate<String, Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    private final AuthenticationManager authenticationManager;

    public String getKey(Long userId) {
        return "system:user-id:" + userId;
    }

    public String getCaptchaKey(String token) {
        return "system:captcha" + token;
    }

    public CaptchaResponse getCaptcha() {
        GifCaptcha gif = CaptchaUtil.createGifCaptcha(150, 50);
        UUID uuid = UUID.fastUUID();

        String captchaKey = getCaptchaKey(uuid.toString());
        stringRedisTemplate.opsForValue().set(captchaKey, gif.getCode());

        CaptchaResponse captcha = new CaptchaResponse();
        captcha.setImg(gif.getImageBase64Data());
        captcha.setToken(uuid.toString());

        return captcha;
    }

    public boolean checkCaptcha(String token, String captchaCode) {
        String captchaKey = getCaptchaKey(token);

        String code = stringRedisTemplate.opsForValue().get(captchaKey);
        redisTemplate.delete(captchaKey);

        return StrUtil.equals(code, captchaCode);



    }

    public LoginUser getUser(Long userId) {
        String key = getKey(userId);
        return (LoginUser) redisTemplate.opsForValue().get(key);
    }

    public void cacheUser(LoginUser loginUser) {
        String key = getKey(loginUser.getUser().getUserId());
        redisTemplate.opsForValue().set(key, loginUser);
    }

    public void logout() {
        Long userId = getUserId();
        String key = getKey(userId);
        redisTemplate.delete(key);
    }

    public static LoginUser getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof LoginUser)) {
            throw new AuthException("未登录", LoginErrorEnum.IllegalState);
        }
        return (LoginUser) principal;
    }


    public static Long getUserId() {
        return getLoginUser().getUser().getUserId();
    }



}
