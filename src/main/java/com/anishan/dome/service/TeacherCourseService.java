package com.anishan.dome.service;

import com.anishan.dome.domain.entity.TeacherCourse;
import com.anishan.dome.domain.vo.EnrollCourse;
import com.anishan.dome.domain.vo.TeacherVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anishan
* @description 针对表【teacher_course(任教表)】的数据库操作Service
* @createDate 2024-12-23 11:02:13
*/
public interface TeacherCourseService extends IService<TeacherCourse> {

    void assign(Long courseId, List<Long> teacherId);

    void reclaim(List<Long> teacherId);

    List<EnrollCourse> getAssignedCourse(Integer year);

    List<TeacherVo> getAssignedTeacher(Long courseId);
}
