package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.MapAjaxResponse;
import com.anishan.dome.domain.dto.ScoreQuery;
import com.anishan.dome.domain.dto.StatisticFrom;
import com.anishan.dome.domain.entity.StudentCourse;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.domain.vo.ScoreVo;
import com.anishan.dome.domain.vo.StatisticResult;
import com.anishan.dome.service.StudentCourseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("成绩相关操作api")
@RestController
@RequestMapping("/teacher/score")
@RequiredArgsConstructor
public class ScoreController {


    private final StudentCourseService studentCourseService;

    @ApiOperation("查询成绩")
    @GetMapping("/list")
    public AjaxResponse<PageResponse<ScoreVo>> list(@Validated ScoreQuery query, @ApiParam("是按照分数否降序，可以为空") Boolean asc) {
        PageResponse<ScoreVo> scores = studentCourseService.queryScore(query, asc);
        return AjaxResponse.ok(scores);

    }


    @ApiOperation("编辑成绩")
    @PutMapping
    public AjaxResponse<Void> updateScore(@Validated  StudentCourse studentCourse) {
        studentCourseService.update(
                new LambdaQueryWrapper<StudentCourse>()
                        .eq(StudentCourse::getUserId, studentCourse.getUserId())
                        .eq(StudentCourse::getTeachId, studentCourse.getTeachId())
        );
        return AjaxResponse.ok(null);
    }


    @ApiOperation("添加成绩")
    @PostMapping
    public AjaxResponse<Void> saveScore(@Validated @RequestBody StudentCourse studentCourse) {
        studentCourseService.save(studentCourse);
        return AjaxResponse.ok(null);
    }

    @ApiOperation("删除成绩")
    @DeleteMapping
    public AjaxResponse<Void> deleteScore(@Validated @RequestBody List<StudentCourse> studentCourse) {
        studentCourseService.removeBatch(studentCourse);
        return AjaxResponse.ok(null);
    }


    @ApiOperation("统计")
    @GetMapping("/statistic")
    public AjaxResponse<PageResponse<StatisticResult>> statistic(@Validated StatisticFrom form ) {
        PageResponse<StatisticResult> statistic = studentCourseService.statistic(form);
        return MapAjaxResponse.ok(statistic);
    }



}
