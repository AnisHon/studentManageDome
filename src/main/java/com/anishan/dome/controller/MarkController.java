package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.MarkDto;
import com.anishan.dome.domain.entity.Mark;
import com.anishan.dome.domain.vo.MarkVo;
import com.anishan.dome.service.MarkService;
import com.anishan.dome.utils.AuthUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Api("辅导员相关操作api")
@RestController
@RequestMapping("/instructor/mark")
@RequiredArgsConstructor
public class MarkController {

    private final MarkService markService;

    @ApiOperation("标记学生")
    @PostMapping
    @ResponseBody
    public AjaxResponse<Void> saveMark(@Validated @RequestBody MarkDto mark) {
        markService.mark(mark);
        return AjaxResponse.ok(null);
    }

    @ApiOperation("更新标记")
    @PutMapping
    @ResponseBody
    public AjaxResponse<Void> updateMark(@Validated @RequestBody MarkDto mark) {
        markService.updateMark(mark);
        return AjaxResponse.ok(null);
    }

    @ApiOperation("删除")
    @DeleteMapping("/{userId}")
    @ResponseBody
    public AjaxResponse<Void> deleteMark(@PathVariable List<Long> userId) {
        markService.deleteBatch(userId);
        return AjaxResponse.ok(null);
    }

    @ApiOperation("列出所有标记的学生")
    @GetMapping
    @ResponseBody
    public AjaxResponse<List<MarkVo>> listMark() {
        List<MarkVo> markVos = markService.listMarked();
        return AjaxResponse.ok(markVos);
    }

    @ApiOperation("通过studentId获取Mark")
    @GetMapping("/{studentId}")
    @ResponseBody
    public AjaxResponse<Mark> getMark(@PathVariable @NotNull Long studentId) {

        Long userId = AuthUtils.getUserId();

        Mark one = markService.getOne(new LambdaQueryWrapper<Mark>()
                .eq(Mark::getStudentId, studentId)
                .eq(Mark::getInstructorId, userId)
        );

        return AjaxResponse.ok(one);
    }




}
