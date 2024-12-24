package com.anishan.dome.mapper;

import com.anishan.dome.domain.entity.Instructor;
import com.anishan.dome.domain.vo.InstructorVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author anishan
* @description 针对表【instructor(用户分表 辅导员表)】的数据库操作Mapper
* @createDate 2024-12-23 11:02:13
* @Entity com.aninshan.dome.domain.entity.Instructor
*/
public interface InstructorMapper extends BaseMapper<Instructor> {

    List<InstructorVo> selectInstructVo(@Param("page") Page<Instructor> page, @Param("ew") LambdaQueryWrapper<Instructor> wrapper);
    Long countTotal(@Param("ew") LambdaQueryWrapper<Instructor> wrapper);
}




