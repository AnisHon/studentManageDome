package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.BaseQueryParam;
import com.anishan.dome.domain.entity.BaseEntity;
import com.anishan.dome.domain.vo.PageResponse;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class BaseController<T, K extends BaseQueryParam<T>> {

    private final IService<T> service;

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询")
    public AjaxResponse<T> get(@PathVariable Long id) {
        return AjaxResponse.ok(service.getById(id));
    }

    @GetMapping("/list")
    @ApiOperation("查询，分页接口")
    public AjaxResponse<PageResponse<T>> list(@Validated K query) {
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
    public AjaxResponse<Boolean> save(@Validated  @RequestBody T entity) {
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
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setCreateTime(LocalDateTime.now());
        }
        return AjaxResponse.ok(service.save(entity));
    }

    @PutMapping("/")
    @ApiOperation("修改接口")
    public AjaxResponse<Boolean> update(@Validated @RequestBody T entity) {
        return AjaxResponse.ok(service.updateById(entity));
    }


}
