package com.anishan.dome.mapper;

import com.anishan.dome.domain.entity.Student;
import com.anishan.dome.domain.entity.Teacher;
import com.anishan.dome.domain.vo.StudentVo;
import com.anishan.dome.domain.vo.TeacherVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
* @author anishan
* @description 针对表【teacher(用户分表 教师表)】的数据库操作Mapper
* @createDate 2024-12-23 11:02:13
* @Entity com.aninshan.dome.domain.entity.Teacher
*/
public interface TeacherMapper extends BaseMapper<Teacher> {

    List<TeacherVo> selectTeacherVo(Page<Teacher> page, LambdaQueryWrapper<Teacher> wrapper);
}




