package com.anishan.dome.utils;

import cn.hutool.core.date.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolYearUtil {

    private final RedisTemplate<String, Object> redisTemplate;


    private static final String KEY = "system:school:year";

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


}
