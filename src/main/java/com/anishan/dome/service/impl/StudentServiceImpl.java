package com.anishan.dome.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.anishan.dome.domain.dto.StudentDto;
import com.anishan.dome.domain.dto.StudentQuery;
import com.anishan.dome.domain.entity.Student;
import com.anishan.dome.domain.entity.SysUser;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.domain.vo.StudentVo;
import com.anishan.dome.enumeration.RoleEnum;
import com.anishan.dome.mapper.StudentMapper;
import com.anishan.dome.service.StudentService;
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
* @description 针对表【student(用户分表 学生表)】的数据库操作Service实现
* @createDate 2024-12-23 11:02:13
*/
@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService {

    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PageResponse<StudentVo> queryVo(StudentQuery query) {
        LambdaQueryWrapper<Student> wrapper = query.queryWrapper();
        Page<Student> page = query.queryPage();
        page.setSearchCount(false);
        List<StudentVo> studentVos = studentMapper.selectStudentVo(page, wrapper);
        Long total = studentMapper.selectCount(new QueryWrapper<>());

        return PageResponse.build(total, studentVos);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeStudentByIds(List<Long> userId) {
        if (CollectionUtil.isEmpty(userId)) {
            return false;
        }
        boolean b = this.removeByIds(userId);
        b &= Db.removeByIds(userId, SysUser.class);
        return b;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveStudent(StudentDto entity) {
        Student student = BeanUtil.copyProperties(entity, Student.class);
        SysUser sysUser = BeanUtil.copyProperties(entity, SysUser.class);
        sysUser.setRole(RoleEnum.Student);
        sysUser.setUserId(null);
        sysUser.setPassword(passwordEncoder.encode(entity.getPassword()));
        boolean b = Db.save(sysUser);
        student.setUserId(sysUser.getUserId());
        b &= this.save(student);
        return b;
    }

    @Override
    @Transactional
    public Boolean updateStudent(StudentDto studentDto) {
        Student student = BeanUtil.copyProperties(studentDto, Student.class);
        SysUser sysUser = BeanUtil.copyProperties(studentDto, SysUser.class);

        boolean b = Db.updateById(sysUser);

        student.setUserId(sysUser.getUserId());
        b &= this.updateById(student);

        return b;
    }
}




