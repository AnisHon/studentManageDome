package com.anishan.dome.domain.vo;

import com.anishan.dome.domain.entity.Major;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClassVo {

    private Long classId;

    private String className;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Major major;

}
