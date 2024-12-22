package com.anishan.dome.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

@Data
public class BaseQueryParam<T> {

    private Long pageNum;
    private Long pageSize;

    public Page<T> queryPage() {
        return Page.of(pageNum, pageSize);
    }

    public LambdaQueryWrapper<T> queryWrapper() {

        QueryWrapper<T> wrapper = new QueryWrapper<>();

        Stream<Method> getters = Arrays
                .stream(this.getClass().getDeclaredMethods())
                .filter(x -> x.getName().startsWith("get"));

        getters.forEach(x -> {
            String column = StringUtils.camelToUnderline(x.getName().replaceFirst("get", ""));

            try {
                Object param = x.invoke(this);

                if (param instanceof String) {
                    wrapper.likeRight(column, param);
                } else {
                    wrapper.eq(param != null, column, param);
                }

            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

        });

        return wrapper.lambda();

    }

}
