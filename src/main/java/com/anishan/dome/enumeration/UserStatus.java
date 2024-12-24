package com.anishan.dome.enumeration;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum UserStatus implements Enum<Integer> {
    Normal(0),
    Banned(1),
    ;

    private final Integer value;
    UserStatus(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
