package com.anishan.dome.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

@Data
@ApiModel("分页基类")
public class BaseQueryParam<T> {

    @NotNull
    @ApiModelProperty("页码")
    private Long pageNum;

    @NotNull
    @ApiModelProperty("页大小")
    private Long pageSize;

    public Page<T> queryPage() {
        return Page.of(pageNum, pageSize);
    }

    public LambdaQueryWrapper<T> queryWrapper() {

        QueryWrapper<T> wrapper = new QueryWrapper<>();

        Class<?> clazz = this.getClass();
        Stream<Method> getters = Arrays
                .stream(clazz.getMethods())
                .filter(x -> x.getName().startsWith("get") && x.getDeclaringClass().equals(clazz));

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
