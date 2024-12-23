package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.ClazzQuery;
import com.anishan.dome.domain.vo.ClassVo;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.service.ClassService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

}
