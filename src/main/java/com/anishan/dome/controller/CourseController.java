package com.anishan.dome.controller;

import com.anishan.dome.domain.dto.CourseQuery;
import com.anishan.dome.domain.entity.Course;
import com.anishan.dome.service.CourseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;


@RestController
@Api("课程接口")
@RequestMapping("/system/course")
public class CourseController extends BaseController<Course, CourseQuery> {


    public CourseController(CourseService service) {
        super(service);
    }

}
