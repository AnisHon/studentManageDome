package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.MajorQuery;
import com.anishan.dome.domain.entity.Major;
import com.anishan.dome.service.MajorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("专业api")
@RequestMapping("/system/major")
public class MajorController extends BaseController<Major, MajorQuery> {

    private final MajorService majorService;

    public MajorController(MajorService majorService) {
        super(majorService);
        this.majorService = majorService;

    }

    @ApiOperation("获取所有的Majro")
    @GetMapping("/all")
    public AjaxResponse<List<Major>> all() {
        return AjaxResponse.ok(majorService.list());
    }

}
