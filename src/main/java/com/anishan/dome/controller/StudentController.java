package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.StudentDto;
import com.anishan.dome.domain.dto.StudentQuery;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.domain.vo.StudentVo;
import com.anishan.dome.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api("学生")
@RequestMapping("/system/student")
public class StudentController {

    @Resource
    private StudentService studentService;




    @GetMapping("/list")
    public AjaxResponse<PageResponse<StudentVo>> list(@Validated StudentQuery query) {
        return AjaxResponse.ok(studentService.queryVo(query));
    }


    @DeleteMapping("/{userId}")
    @ApiOperation("删除接口")
    public AjaxResponse<Boolean> del(@Validated @PathVariable("userId") List<Long> userId) {
        return AjaxResponse.ok(studentService.removeStudentByIds(userId));
    }

    @PostMapping("/")
    @ApiOperation("添加接口")
    public AjaxResponse<Boolean> save(@Validated @RequestBody StudentDto entity) {
        return AjaxResponse.ok(studentService.saveStudent(entity));
    }

    @PutMapping("/")
    @ApiOperation("修改接口")
    public AjaxResponse<Boolean> update(@Validated(Update.class) @RequestBody StudentDto entity) {
        return AjaxResponse.ok(studentService.updateStudent(entity));
    }


}
