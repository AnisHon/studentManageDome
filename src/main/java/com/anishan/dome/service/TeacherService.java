package com.anishan.dome.service;

import com.anishan.dome.domain.dto.StudentQuery;
import com.anishan.dome.domain.dto.TeacherDto;
import com.anishan.dome.domain.dto.TeacherQuery;
import com.anishan.dome.domain.entity.Teacher;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.domain.vo.TeacherVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anishan
* @description 针对表【teacher(用户分表 教师表)】的数据库操作Service
* @createDate 2024-12-23 11:02:13
*/
public interface TeacherService extends IService<Teacher> {

    PageResponse<TeacherVo> queryVo(TeacherQuery query);

    Boolean removeTeacherByIds(List<Long> userId);

    Boolean saveTeacher(TeacherDto entity);

    Boolean updateTeacher(TeacherDto entity);
}
