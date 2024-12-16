package com.anishan.dome.utils;

import com.anishan.dome.domain.LoginedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthUtils {

    private static final Map<Long, LoginedUser> loginUsers = new HashMap<>();

    public void login(Long userId, LoginedUser user, String token) {
        if (userId == null || user == null) {
            return;
        }
        loginUsers.put(userId, user);
    }

    public void logout(Long userId, String token) {
        if (userId == null) {
            return;
        }
        loginUsers.remove(userId);
    }

    public LoginedUser getLoginUser(Long userId) {
        return loginUsers.get(userId);
    }

}
