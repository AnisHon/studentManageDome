package com.anishan.dome.mapper;

import com.anishan.dome.domain.entity.Student;
import com.anishan.dome.domain.vo.StudentVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author anishan
* @description 针对表【student(用户分表 学生表)】的数据库操作Mapper
* @createDate 2024-12-23 11:02:13
* @Entity com.aninshan.dome.domain.entity.Student
*/
public interface StudentMapper extends BaseMapper<Student> {

    List<StudentVo> selectStudentVo(@Param("page") Page<Student> page, @Param("ew") Wrapper<Student> wrapper);


}




