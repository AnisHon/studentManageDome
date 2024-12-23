package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.UserPageQuery;
import com.anishan.dome.domain.entity.SysUser;
import com.anishan.dome.enumeration.RoleEnum;
import com.anishan.dome.exception.BusinessException;
import com.anishan.dome.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public AjaxResponse<Boolean> save(SysUser entity) {
        if (entity.getRole() != RoleEnum.Admin) {
            throw new BusinessException("使用用户管理添加的用户一定是管理员");
        }

        String encode = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encode);
        return super.save(entity);
    }

    @Override
    public AjaxResponse<Boolean> update(SysUser entity) {
        if (entity.getRole() != RoleEnum.Admin) {
            throw new BusinessException("使用用户管理添加的用户一定是管理员");
        }


        String encode = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encode);
        return super.update(entity);
    }
}
