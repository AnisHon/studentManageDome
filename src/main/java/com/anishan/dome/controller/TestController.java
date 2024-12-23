package com.anishan.dome.controller;

import com.anishan.dome.domain.MapAjaxResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api("测试")
public class TestController {


    @GetMapping("/")
    public MapAjaxResponse test() {
        return MapAjaxResponse
                .ok()
                .add("test", "test")
                .add("test2", "test2");
    }


}
