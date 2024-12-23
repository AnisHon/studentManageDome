package com.anishan.dome.service;

import com.anishan.dome.domain.dto.ClazzQuery;
import com.anishan.dome.domain.entity.Clazz;
import com.anishan.dome.domain.vo.ClassVo;
import com.anishan.dome.domain.vo.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anishan
* @description 针对表【class(班级表)】的数据库操作Service
* @createDate 2024-12-23 11:02:13
*/
public interface ClassService extends IService<Clazz> {

    List<ClassVo> all();

    PageResponse<ClassVo> listVo(ClazzQuery query);
}
