package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.BaseQueryParam;
import com.anishan.dome.domain.vo.PageResponse;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class BaseController<T, K extends BaseQueryParam<T>> {

    private final IService<T> service;

    @GetMapping("/list")
    @ApiOperation("查询，分页接口")
    protected AjaxResponse<PageResponse<T>> list(K query) {
        System.out.println(query);
        Page<T> tPage = query.queryPage();
        LambdaQueryWrapper<T> wrapper = query.queryWrapper();

        Page<T> page = service.page(tPage, wrapper);

        return AjaxResponse.ok(PageResponse.fromPage(page));
    }

    @DeleteMapping("/{userId}")
    @ApiOperation("删除接口")
    public AjaxResponse<Boolean> del(@PathVariable("userId") List<Long> userId) {
        return AjaxResponse.ok(service.removeByIds(userId));
    }

    @PostMapping("/")
    @ApiOperation("添加接口")
    public AjaxResponse<Boolean> save(@RequestBody T entity) {
        Arrays
                .stream(entity.getClass().getDeclaredFields())
                .filter(x -> x.isAnnotationPresent(TableId.class))
                .findFirst()
                .ifPresent(id -> {
                    try {
                        id.setAccessible(true);
                        id.set(entity, null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        return AjaxResponse.ok(service.save(entity));
    }

    @PutMapping("/")
    @ApiOperation("修改接口")
    public AjaxResponse<Boolean> update(@RequestBody T entity) {
        return AjaxResponse.ok(service.updateById(entity));
    }


}
