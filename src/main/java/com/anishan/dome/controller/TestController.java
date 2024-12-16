package com.anishan.dome.controller;

import com.anishan.dome.domain.MapAjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {


    @GetMapping("/")
    public MapAjaxResult test() {
        return MapAjaxResult
                .ok()
                .add("test", "test")
                .add("test2", "test2");
    }


}
