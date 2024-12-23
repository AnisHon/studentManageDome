package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.MapAjaxResponse;
import com.anishan.dome.domain.dto.QueryEnrollCourse;
import com.anishan.dome.domain.entity.StudentCourse;
import com.anishan.dome.domain.vo.EnrollCourse;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.service.CourseService;
import com.anishan.dome.service.StudentCourseService;
import com.anishan.dome.service.StudentService;
import com.anishan.dome.utils.AuthUtils;
import com.anishan.dome.utils.SchoolYearUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Api("选课API")
@RestController
@RequestMapping("/student/course")
@RequiredArgsConstructor
public class CourseSelectionController {

    private final StudentCourseService studentCourseService;
    private final SchoolYearUtil schoolYearUtil;


    @GetMapping("/school-year")
    @ApiOperation("查看当前学年")
    public MapAjaxResponse getSchoolYear() {
        return MapAjaxResponse
                .ok()
                .add("schoolYear", schoolYearUtil.getSchoolYear());
    }

    @PutMapping("/enroll/{teachId}")
    @ApiOperation("选课")
    public AjaxResponse<Boolean> enroll(@NotNull @PathVariable List<Long> teachId) {
        Long userId = AuthUtils.getUserId();

        boolean b = studentCourseService.enroll(userId, teachId);
        return AjaxResponse.ok(b);
    }

    @DeleteMapping("/drop/{teachId}")
    @ApiOperation("退课")
    public AjaxResponse<Void> drop(@NotNull @PathVariable List<Long> teachId) {
        Long userId = AuthUtils.getUserId();
        studentCourseService.drop(userId, teachId);
        return AjaxResponse.ok(null);
    }

    @GetMapping("/enrolled/{schoolYear}")
    @ApiOperation("查看所选的课")
    public AjaxResponse<List<EnrollCourse>> list(@NotNull @PathVariable String schoolYear) {
        Long userId = AuthUtils.getUserId();
        List<EnrollCourse> list = studentCourseService.listEnrolled(userId);
        return AjaxResponse.ok(list);
    }

    @GetMapping()
    @ApiOperation("查看所有能选的课")
    public AjaxResponse<PageResponse<EnrollCourse>> list(@Validated QueryEnrollCourse query) {
        PageResponse<EnrollCourse> courses = studentCourseService.listAvailable(query);
        return AjaxResponse.ok(courses);
    }


}
