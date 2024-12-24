package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.vo.EnrollCourse;
import com.anishan.dome.domain.vo.TeacherVo;
import com.anishan.dome.service.TeacherCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api("教师任教")
@RequestMapping("/system/course")
@RequiredArgsConstructor
public class TeachController {

    private final TeacherCourseService teacherCourseService;

    @PostMapping("/assign/{courseId}/{teacherId}")
    @ApiOperation("分配课程")
    public AjaxResponse<Void> assign(@PathVariable Long courseId, @PathVariable List<Long> teacherId) {
        teacherCourseService.assign(courseId, teacherId);
        return AjaxResponse.ok(null);
    }

    @DeleteMapping("/reclaim/{teachId}")
    @ApiOperation("取消课程")
    public AjaxResponse<Void> reclaim(@PathVariable List<Long> teachId) {
        teacherCourseService.reclaim(teachId);
        return AjaxResponse.ok(null);
    }

    @GetMapping("/course/{schoolYear}")
    @ApiOperation("教师查看分配的课")
    public AjaxResponse<List<EnrollCourse>> getAssignedCourses(@PathVariable Integer schoolYear) {
        List<EnrollCourse> teacherAssigned = teacherCourseService.getAssignedCourse(schoolYear);
        return AjaxResponse.ok(teacherAssigned);
    }

    @GetMapping("/teacher/{courseId}")
    @ApiOperation("查看某个课程分配的教师")
    public AjaxResponse<List<TeacherVo>> getAssignedTeacher(@PathVariable Long courseId) {
        List<TeacherVo> courseAssigned = teacherCourseService.getAssignedTeacher(courseId);
        return AjaxResponse.ok(courseAssigned);
    }

}
