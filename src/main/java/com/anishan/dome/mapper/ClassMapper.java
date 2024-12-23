package com.anishan.dome.mapper;

import com.anishan.dome.controller.ClassController;
import com.anishan.dome.domain.dto.ClazzQuery;
import com.anishan.dome.domain.entity.Clazz;
import com.anishan.dome.domain.vo.ClassVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author anishan
* @description 针对表【class(班级表)】的数据库操作Mapper
* @createDate 2024-12-23 11:02:13
* @Entity com.aninshan.dome.domain.entity.ClazzQuery
*/
public interface ClassMapper extends BaseMapper<Clazz> {

    List<ClassVo> selectClassVoAll();
    List<ClassVo> selectClassVo(@Param("page") Page<Clazz> page, @Param("ew")Wrapper<Clazz> wrapper);
}




