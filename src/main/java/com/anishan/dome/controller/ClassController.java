package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.ClazzQuery;
import com.anishan.dome.domain.entity.BaseEntity;
import com.anishan.dome.domain.entity.Clazz;
import com.anishan.dome.domain.vo.ClassVo;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.service.ClassService;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/system/class")
@Api("班级api")
public class ClassController {

    @Resource
    private ClassService classService;


    @ApiModelProperty("获取所有班级")
    @GetMapping("/all")
    private AjaxResponse<List<ClassVo>> all() {
        return AjaxResponse.ok(classService.all());
    }


    @GetMapping("/list")
    @ApiOperation("查询，分页接口")
    public AjaxResponse<PageResponse<ClassVo>> list(@Validated ClazzQuery query) {
        return AjaxResponse.ok(classService.listVo(query));
    }

    @PostMapping
    @ApiOperation("添加")
    public AjaxResponse<Boolean> save(@Validated({Insert.class}) @RequestBody Clazz entity) {
        entity.setClassId(null);
        return AjaxResponse.ok(classService.save(entity));
    }

    @PutMapping
    @ApiOperation("更新修改接口")
    public AjaxResponse<Boolean> update(@Validated({Update.class}) @RequestBody Clazz entity) {
        return AjaxResponse.ok(classService.updateById(entity));
    }

    @GetMapping("/{classId}")
    public AjaxResponse<Clazz> get(@PathVariable Long classId) {
        return AjaxResponse.ok(classService.getById(classId));
    }

}
