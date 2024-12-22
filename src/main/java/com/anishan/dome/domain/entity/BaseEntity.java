package com.anishan.dome.domain.entity;

import cn.hutool.core.date.DateTime;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
public class BaseEntity {


    @ModelAttribute()
    private DateTime createTime;
    private DateTime updateTime;


}
