package com.anishan.dome.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.anishan.dome.domain.dto.InstructorDto;
import com.anishan.dome.domain.dto.InstructorQuery;
import com.anishan.dome.domain.entity.Instructor;
import com.anishan.dome.domain.entity.SysUser;
import com.anishan.dome.domain.vo.InstructorVo;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.enumeration.RoleEnum;
import com.anishan.dome.mapper.InstructorMapper;
import com.anishan.dome.service.InstructorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author anishan
* @description 针对表【instructor(用户分表 辅导员表)】的数据库操作Service实现
* @createDate 2024-12-23 11:02:13
*/
@Service
public class InstructorServiceImpl extends ServiceImpl<InstructorMapper, Instructor>
    implements InstructorService {

    private final InstructorMapper instructorMapper;
    private final PasswordEncoder passwordEncoder;

    public InstructorServiceImpl(InstructorMapper instructorMapper, PasswordEncoder passwordEncoder) {
        this.instructorMapper = instructorMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PageResponse<InstructorVo> queryVo(InstructorQuery query) {
        LambdaQueryWrapper<Instructor> wrapper = query.queryWrapper();
        Page<Instructor> page = query.queryPage();
        page.setSearchCount(false);
        List<InstructorVo> instructorVos = instructorMapper.selectInstructVo(page, wrapper);
        Long total = instructorMapper.countTotal(wrapper);

        return PageResponse.build(total, instructorVos);
    }

    @Override
    public Boolean removeInstructorByIds(List<Long> userId) {
        if (CollectionUtil.isEmpty(userId)) {
            return false;
        }
        boolean b = this.removeByIds(userId);
        b &= Db.removeByIds(userId, SysUser.class);
        return b;
    }

    @Override
    public Boolean saveInstructor(InstructorDto entity) {
        Instructor student = BeanUtil.copyProperties(entity, Instructor.class);
        SysUser sysUser = BeanUtil.copyProperties(entity, SysUser.class);
        sysUser.setRole(RoleEnum.Instructor);
        sysUser.setUserId(null);
        sysUser.setPassword(passwordEncoder.encode(entity.getPassword()));
        boolean b = Db.save(sysUser);
        student.setUserId(sysUser.getUserId());
        b &= this.save(student);
        return b;
    }

    @Override
    public Boolean updateInstructor(InstructorDto entity) {
        Instructor student = BeanUtil.copyProperties(entity, Instructor.class);
        SysUser sysUser = BeanUtil.copyProperties(entity, SysUser.class);

        boolean b = Db.updateById(sysUser);

        student.setUserId(sysUser.getUserId());
        b &= this.updateById(student);

        return b;
    }
}




