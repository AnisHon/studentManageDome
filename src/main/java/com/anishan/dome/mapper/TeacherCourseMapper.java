package com.anishan.dome.mapper;

import com.anishan.dome.domain.entity.Course;
import com.anishan.dome.domain.entity.TeacherCourse;
import com.anishan.dome.domain.vo.EnrollCourse;
import com.anishan.dome.domain.vo.TeacherVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author anishan
* @description 针对表【teacher_course(任教表)】的数据库操作Mapper
* @createDate 2024-12-23 11:02:13
* @Entity com.aninshan.dome.domain.entity.TeacherCourse
*/
public interface TeacherCourseMapper extends BaseMapper<TeacherCourse> {

    List<EnrollCourse> selectCourse(@Param("teacherId") Long teacherId, @Param("schoolYear") Integer schoolYear);

    List<TeacherVo> selectTeacher(@Param("courseId") Long courseId);
}




