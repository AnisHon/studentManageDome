package com.anishan.dome.enumeration;

import lombok.Getter;

// 学生 1 辅导员 5 教师 10 管理员 20
@Getter
public enum RoleEnum {
    Student(1),
    Instructor(5),
    Teacher(10),
    Admin(20),
    ;

    private final int value;
    RoleEnum(int value) {
        this.value = value;
    }

}
