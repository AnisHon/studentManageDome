package com.anishan.dome.utils;

import cn.hutool.core.date.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SchoolYearUtil {

    private final RedisTemplate<String, Object> redisTemplate;


    private static final String KEY = "system:school:year";
    private static final String START = "system:school:start";


    public void setSchoolYear(int year) {
        redisTemplate.opsForValue().set(KEY, year);
    }

    public int getSchoolYear() {
        Integer year = (Integer) redisTemplate.opsForValue().get(KEY);
        if (year == null) {
            return DateUtil.thisYear();
        }
        return year;
    }


    public Boolean isStarted() {
        return (Boolean) Optional.ofNullable(redisTemplate.opsForValue().get(START)).orElse(Boolean.FALSE);
    }

    public void setStart(boolean start) {
        redisTemplate.opsForValue().set(START, start);
    }
}
