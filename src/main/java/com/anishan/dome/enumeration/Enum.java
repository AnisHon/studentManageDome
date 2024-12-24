package com.anishan.dome.enumeration;


import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface Enum<T extends Serializable> extends IEnum<T> {
    @JsonValue
    T getValue();
}
