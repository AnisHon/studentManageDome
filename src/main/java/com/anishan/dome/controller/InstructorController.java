package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.*;
import com.anishan.dome.domain.vo.InstructorVo;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.service.InstructorService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/system/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @ResponseBody
    @GetMapping("/{userId}")
    @ApiOperation("获取接口")
    public AjaxResponse<InstructorVo> getObj(@PathVariable Long userId) {
        InstructorQuery instructorQuery = new InstructorQuery();
        instructorQuery.setUserId(userId);
        instructorQuery.setPageNum(1L);
        instructorQuery.setPageSize(10L);
        PageResponse<InstructorVo> instructorVoPageResponse = instructorService.queryVo(instructorQuery);
        List<InstructorVo> rows = instructorVoPageResponse.getRows();
        if (rows.isEmpty()) {
            return AjaxResponse.ok(null);
        } else {
            return AjaxResponse.ok(rows.get(0));
        }
    }


    @ResponseBody
    @GetMapping("/list")
    @ApiModelProperty("查询接口")
    public AjaxResponse<PageResponse<InstructorVo>> list(@Validated InstructorQuery query) {
        return AjaxResponse.ok(instructorService.queryVo(query));
    }

    @ResponseBody
    @DeleteMapping("/{userId}")
    @ApiOperation("删除接口")
    public AjaxResponse<Boolean> del(@Validated @PathVariable("userId") List<Long> userId) {
        return AjaxResponse.ok(instructorService.removeInstructorByIds(userId));
    }

    @ResponseBody
    @PostMapping
    @ApiOperation("添加接口")
    public AjaxResponse<Boolean> save(@Validated @RequestBody InstructorDto entity) {
        return AjaxResponse.ok(instructorService.saveInstructor(entity));
    }

    @ResponseBody
    @PutMapping
    @ApiOperation("修改接口")
    public AjaxResponse<Boolean> update(@Validated(Update.class) @RequestBody InstructorDto entity) {
        return AjaxResponse.ok(instructorService.updateInstructor(entity));
    }
}
