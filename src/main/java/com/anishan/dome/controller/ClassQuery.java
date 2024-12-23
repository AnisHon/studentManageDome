package com.anishan.dome.controller;

import com.anishan.dome.domain.dto.ClazzQuery;
import com.anishan.dome.domain.entity.Clazz;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/class")
@Api("班级api")
public class ClassQuery extends BaseController<Clazz, ClazzQuery> {

    @Autowired
    public ClassQuery(IService<Clazz> service) {
        super(service);
    }
}
