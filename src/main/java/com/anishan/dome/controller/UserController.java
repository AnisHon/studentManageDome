package com.anishan.dome.controller;

import com.anishan.dome.domain.dto.UserPageQuery;
import com.anishan.dome.domain.entity.SysUser;
import com.anishan.dome.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("用户api")
@RequestMapping("/system/user")
public class UserController extends BaseController<SysUser, UserPageQuery> {

    @Autowired
    public UserController(SysUserService service) {
        super(service);
    }
}
