package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.MarkDto;
import com.anishan.dome.domain.vo.MarkVo;
import com.anishan.dome.service.MarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("辅导员相关操作api")
@RestController
@RequestMapping("/instructor/mark")
@RequiredArgsConstructor
public class MarkController {

    private final MarkService markService;

    @ApiOperation("标记学生")
    @PostMapping
    public AjaxResponse<Void> saveMark(@Validated MarkDto mark) {
        markService.mark(mark);
        return AjaxResponse.ok(null);
    }

    @ApiOperation("更新标记")
    @PutMapping
    public AjaxResponse<Void> updateMark(@Validated MarkDto mark) {
        markService.updateMark(mark);
        return AjaxResponse.ok(null);
    }

    @ApiOperation("删除")
    @DeleteMapping("/{userId}")
    public AjaxResponse<Void> deleteMark(List<Long> userId) {
        markService.deleteBatch(userId);
        return AjaxResponse.ok(null);
    }

    @ApiOperation("列出所有标记的学生")
    @GetMapping
    public AjaxResponse<List<MarkVo>> listMark() {
        List<MarkVo> markVos = markService.listMarked();
        return AjaxResponse.ok(markVos);
    }




}
