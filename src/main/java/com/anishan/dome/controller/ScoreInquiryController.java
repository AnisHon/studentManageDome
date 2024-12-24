package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.ScoreQuery;
import com.anishan.dome.domain.vo.ScoreVo;
import com.anishan.dome.service.StudentCourseService;
import com.anishan.dome.utils.AuthUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("学生查成绩API")
@RestController
@RequestMapping("/student/score")
@RequiredArgsConstructor
public class ScoreInquiryController {

    private final StudentCourseService studentCourseService;

    @ApiOperation("查看分数")
    @GetMapping
    public AjaxResponse<List<ScoreVo>> getScoreInquiry() {
        ScoreQuery scoreQuery = new ScoreQuery();
        scoreQuery.setPageNum(1L);
        scoreQuery.setPageSize(100L);
//        Long userId = AuthUtils.getUserId();
        scoreQuery.setUserId(1L);
        List<ScoreVo> rows = studentCourseService.queryScore(scoreQuery, null).getRows();
        return AjaxResponse.ok(rows);
    }

}
