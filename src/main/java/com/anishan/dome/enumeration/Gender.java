package com.anishan.dome.enumeration;

import lombok.Getter;

@Getter
public enum Gender implements Enum<Integer> {

    Male(0), Female(1);

    private final Integer value;
    Gender(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
