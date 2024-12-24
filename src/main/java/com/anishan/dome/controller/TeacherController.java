package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.StudentQuery;
import com.anishan.dome.domain.dto.TeacherDto;
import com.anishan.dome.domain.dto.TeacherQuery;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.domain.vo.StudentVo;
import com.anishan.dome.domain.vo.TeacherVo;
import com.anishan.dome.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api("教师api")
@RequestMapping("/system/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @GetMapping("/{userId}")
    @ApiOperation("获取接口")
    public AjaxResponse<TeacherVo> get(@PathVariable Long userId) {
        TeacherQuery teacherQuery = new TeacherQuery();
        teacherQuery.setUserId(userId);
        teacherQuery.setPageNum(1L);
        teacherQuery.setPageSize(10L);
        PageResponse<TeacherVo> teacherVoPageResponse = teacherService.queryVo(teacherQuery);
        List<TeacherVo> rows = teacherVoPageResponse.getRows();
        if (rows.isEmpty()) {
            return AjaxResponse.ok(null);
        } else {
            return AjaxResponse.ok(rows.get(0));
        }
    }



    @GetMapping("/list")
    public AjaxResponse<PageResponse<TeacherVo>> list(@Validated TeacherQuery query) {
        return AjaxResponse.ok(teacherService.queryVo(query));
    }


    @DeleteMapping("/{userId}")
    @ApiOperation("删除接口")
    public AjaxResponse<Boolean> del(@Validated @PathVariable("userId") List<Long> userId) {
        return AjaxResponse.ok(teacherService.removeTeacherByIds(userId));
    }

    @PostMapping("/")
    @ApiOperation("添加接口")
    public AjaxResponse<Boolean> save(@Validated @RequestBody TeacherDto entity) {
        return AjaxResponse.ok(teacherService.saveTeacher(entity));
    }

    @PutMapping("/")
    @ApiOperation("修改接口")
    public AjaxResponse<Boolean> update(@Validated(Update.class) @RequestBody TeacherDto entity) {
        return AjaxResponse.ok(teacherService.updateTeacher(entity));
    }


}
