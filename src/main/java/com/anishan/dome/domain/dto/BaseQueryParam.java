package com.anishan.dome.domain.dto;

import com.anishan.dome.annotation.SelectAlias;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
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

        return plainQueryWrapper().lambda();

    }

    public QueryWrapper<T> plainQueryWrapper() {
        QueryWrapper<T> wrapper = new QueryWrapper<>();

        Class<?> clazz = this.getClass();
        Stream<Method> getters = Arrays
                .stream(clazz.getMethods())
                .filter(x -> {
                    boolean isGetter = x.getName().startsWith("get") || x.getName().startsWith("is");
                    return isGetter && x.getDeclaringClass().equals(clazz);
                });
        getters.forEach(x -> {

            String methodName = x.getName();
            String fieldName;

            if (methodName.startsWith("get")) {
                fieldName = methodName.substring(3);
            } else if (methodName.startsWith("is")) {
                fieldName = methodName.substring(2);
            } else {
                return;
            }

            fieldName = StringUtils.firstToLowerCase(fieldName);


            String column = StringUtils.camelToUnderline(fieldName);
            try {

                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                SelectAlias annotation = field.getAnnotation(SelectAlias.class);
                if (annotation != null) {
                    column = annotation.value() + "." + column;
                }
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
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

        return wrapper;

    }

}
