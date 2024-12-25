package com.anishan.dome.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.MapAjaxResponse;
import com.anishan.dome.domain.dto.ScoreQuery;
import com.anishan.dome.domain.dto.StatisticFrom;
import com.anishan.dome.domain.entity.StudentCourse;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.domain.vo.ScoreVo;
import com.anishan.dome.domain.vo.StatisticResult;
import com.anishan.dome.exception.BusinessException;
import com.anishan.dome.service.StudentCourseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

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

    @ApiOperation("导出成绩")
    @GetMapping("/export")
    @SneakyThrows
    public void exportScore(@Validated ScoreQuery query, @ApiParam("是按照分数否降序，可以为空") Boolean asc, HttpServletResponse response) {
        query.setPageNum(1L);
        query.setPageSize(300L);

        PageResponse<ScoreVo> scores = studentCourseService.queryScore(query, asc);

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=score.xlsx");


        if (CollUtil.isEmpty(scores.getRows())) {
            throw new BusinessException("您所查询的选项没有数据，无法导出");
        }



        EasyExcel.write(response.getOutputStream(), ScoreVo.class)
                .sheet("学生成绩")
                .doWrite(scores.getRows());
    }


    @ApiOperation("导入成绩")
    @PutMapping("/import")
    @SneakyThrows
    public void exportScore(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException("没有文件");
        }
        InputStream inputStream = file.getInputStream();
        List<StudentCourse> score = EasyExcel
                .read(inputStream)
                .head(ScoreVo.class)
                .sheet(0)
                .doReadSync()
                .stream()
                .map(
                        x -> {
                            ScoreVo vo = (ScoreVo)x;
                            return new StudentCourse()
                                    .setTeachId(vo.getTeachId())
                                    .setScore(vo.getScore())
                                    .setUserId(vo.getStudentId());
                        }
                ).collect(Collectors.toList());

        studentCourseService.saveBatch(score);

    }





    @ApiOperation("编辑成绩")
    @PutMapping
    public AjaxResponse<Void> updateScore(@Validated @RequestBody StudentCourse studentCourse) {
        studentCourseService.update(
                new LambdaUpdateWrapper<StudentCourse>()
                        .set(StudentCourse::getScore, studentCourse.getScore())
                        .eq(StudentCourse::getUserId, studentCourse.getUserId())
                        .eq(StudentCourse::getTeachId, studentCourse.getTeachId())

        );
        return AjaxResponse.ok(null);
    }


    @ApiOperation("添加成绩")
    @PostMapping
    public AjaxResponse<Void> saveScore(@Validated @RequestBody StudentCourse studentCourse) {

        boolean exists = studentCourseService.exists(new LambdaQueryWrapper<StudentCourse>()
                .eq(StudentCourse::getTeachId, studentCourse.getTeachId())
                .eq(StudentCourse::getUserId, studentCourse.getUserId())

        );

        if (!exists) {
            throw new BusinessException("该选课不存在");
        }

        studentCourseService.update(
                new LambdaUpdateWrapper<StudentCourse>()
                        .set(StudentCourse::getScore, studentCourse.getScore())
                        .eq(StudentCourse::getTeachId, studentCourse.getTeachId())
                        .eq(StudentCourse::getUserId, studentCourse.getUserId())
                        .isNotNull(StudentCourse::getScore)
        );
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

    @SneakyThrows
    @ApiOperation("导出查询结果")
    @GetMapping("/statistic/export")
    public void export(@Validated StatisticFrom form, HttpServletResponse response) {
        PageResponse<StatisticResult> statistic = studentCourseService.statistic(form);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=export.xlsx");

        if (CollUtil.isEmpty(statistic.getRows())) {
            throw new BusinessException("您所查询的选项没有数据，无法导出");
        }

        EasyExcel.write(response.getOutputStream(), StatisticResult.class)
                .sheet("学生成绩")
                .doWrite(statistic.getRows());

    }



}
