package com.anishan.dome.controller;

import com.anishan.dome.domain.AjaxResponse;
import com.anishan.dome.domain.dto.StatusForm;
import com.anishan.dome.domain.dto.UserPageQuery;
import com.anishan.dome.domain.entity.SysUser;
import com.anishan.dome.enumeration.RoleEnum;
import com.anishan.dome.exception.BusinessException;
import com.anishan.dome.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api("用户api")
@RequestMapping("/system/user")
public class UserController extends BaseController<SysUser, UserPageQuery> {

    @Resource
    private PasswordEncoder passwordEncoder;

    private final SysUserService sysUserService;

    @Autowired
    public UserController(SysUserService service) {
        super(service);
        this.sysUserService = service;
    }



    @Override
    @ResponseBody
    @PostMapping
    public AjaxResponse<Boolean> save(@RequestBody SysUser entity) {
        if (entity.getRole() != RoleEnum.Admin) {
            throw new BusinessException("这里只能添加管理员账户");
        }

        String encode = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encode);
        return super.save(entity);
    }

    @Override
    @ResponseBody
    @PutMapping
    public AjaxResponse<Boolean> update(@RequestBody SysUser entity) {
        entity.setRole(null);

        String encode = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encode);
        return super.update(entity);
    }


    @ResponseBody
    @PutMapping("/changeStatus")
    @ApiOperation("更改状态")
    public AjaxResponse<Void> changeStatus(@RequestBody StatusForm statusForm) {
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<SysUser>()
                .set(SysUser::getStatus, statusForm.getStatus())
                .eq(SysUser::getUserId, statusForm.getUserId());

        sysUserService.update(updateWrapper);
        return AjaxResponse.ok(null);
    }



}
