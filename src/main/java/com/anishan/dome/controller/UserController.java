package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.UserPageQuery;
import com.anishan.dome.domain.entity.SysUser;
import com.anishan.dome.enumeration.RoleEnum;
import com.anishan.dome.exception.BusinessException;
import com.anishan.dome.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api("用户api")
@RequestMapping("/system/user")
public class UserController extends BaseController<SysUser, UserPageQuery> {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(SysUserService service) {
        super(service);
    }



    @Override
    public AjaxResponse<Boolean> save(@RequestBody SysUser entity) {
        if (entity.getRole() != RoleEnum.Admin) {
            throw new BusinessException("这里只能添加管理员账户");
        }

        String encode = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encode);
        return super.save(entity);
    }

    @Override
    public AjaxResponse<Boolean> update(@RequestBody SysUser entity) {
        entity.setRole(null);

        String encode = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encode);
        return super.update(entity);
    }



}
