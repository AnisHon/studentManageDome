package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.utils.SchoolYearUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/system")
public class AdminController {

    private final SchoolYearUtil schoolYearUtil;

    public AdminController(SchoolYearUtil schoolYearUtil) {
        this.schoolYearUtil = schoolYearUtil;
    }

    @ApiOperation("设置school year")
    @PutMapping("/{schoolYear}")
    public AjaxResponse<Void> setSchoolYear(@PathVariable @NotNull Integer schoolYear) {
        schoolYearUtil.setSchoolYear(schoolYear);
        return AjaxResponse.ok(null);
    }

    @ApiOperation("设置选课状态")
    @PutMapping("/{status}")
    public AjaxResponse<Void> setStatus(@PathVariable @NotNull Boolean status) {
        schoolYearUtil.setStart(status);
        return AjaxResponse.ok(null);
    }

}
