package com.anishan.dome.service;

import com.anishan.dome.domain.dto.InstructorDto;
import com.anishan.dome.domain.dto.InstructorQuery;
import com.anishan.dome.domain.entity.Instructor;
import com.anishan.dome.domain.vo.InstructorVo;
import com.anishan.dome.domain.vo.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anishan
* @description 针对表【instructor(用户分表 辅导员表)】的数据库操作Service
* @createDate 2024-12-23 11:02:13
*/
public interface InstructorService extends IService<Instructor> {

    PageResponse<InstructorVo> queryVo(InstructorQuery query);

    Boolean removeInstructorByIds(List<Long> userId);

    Boolean saveInstructor(InstructorDto entity);

    Boolean updateInstructor(InstructorDto entity);
}
