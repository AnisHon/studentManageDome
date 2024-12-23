package com.anishan.dome.controller;

import com.anishan.dome.domain.dto.MajorQuery;
import com.anishan.dome.domain.entity.Major;
import com.anishan.dome.service.MajorService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("专业api")
@RequestMapping("/system/major")
public class MajorController extends BaseController<Major, MajorQuery> {
    public MajorController(MajorService majorService) {
        super(majorService);
    }
}
