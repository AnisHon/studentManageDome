package com.anishan.dome.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.anishan.dome.domain.dto.StudentQuery;
import com.anishan.dome.domain.dto.TeacherDto;
import com.anishan.dome.domain.dto.TeacherQuery;
import com.anishan.dome.domain.entity.Student;
import com.anishan.dome.domain.entity.SysUser;
import com.anishan.dome.domain.entity.Teacher;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.domain.vo.TeacherVo;
import com.anishan.dome.enumeration.RoleEnum;
import com.anishan.dome.mapper.TeacherMapper;
import com.anishan.dome.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author anishan
* @description 针对表【teacher(用户分表 教师表)】的数据库操作Service实现
* @createDate 2024-12-23 11:02:13
*/
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService {
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PageResponse<TeacherVo> queryVo(TeacherQuery query) {
        LambdaQueryWrapper<Teacher> wrapper = query.queryWrapper();
        Page<Teacher> page = query.queryPage();
        page.setSearchCount(false);
        List<TeacherVo> teacherVo = teacherMapper.selectTeacherVo(page, wrapper);
        Long total = teacherMapper.countTotal(wrapper);

        return PageResponse.build(total, teacherVo);
    }

    @Override
    public Boolean removeTeacherByIds(List<Long> userId) {
        if (CollectionUtil.isEmpty(userId)) {
            return false;
        }
        boolean b = this.removeByIds(userId);
        b &= Db.removeByIds(userId, SysUser.class);
        return b;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveTeacher(TeacherDto entity) {
        Teacher teacher = BeanUtil.copyProperties(entity, Teacher.class);
        SysUser sysUser = BeanUtil.copyProperties(entity, SysUser.class);
        sysUser.setRole(RoleEnum.Teacher);
        sysUser.setUserId(null);
        sysUser.setPassword(passwordEncoder.encode(entity.getPassword()));
        boolean b = Db.save(sysUser);
        teacher.setUserId(sysUser.getUserId());
        b &= this.save(teacher);
        return b;
    }

    @Override
    public Boolean updateTeacher(TeacherDto entity) {
        Teacher teacher = BeanUtil.copyProperties(entity, Teacher.class);
        SysUser sysUser = BeanUtil.copyProperties(entity, SysUser.class);

        boolean b = Db.updateById(sysUser);
        sysUser.setPassword(passwordEncoder.encode(entity.getPassword()));

        teacher.setUserId(sysUser.getUserId());
        b &= this.updateById(teacher);

        return b;
    }
}




