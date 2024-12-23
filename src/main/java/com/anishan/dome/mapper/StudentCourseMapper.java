package com.anishan.dome.mapper;

import com.anishan.dome.domain.entity.StudentCourse;
import com.anishan.dome.domain.entity.TeacherCourse;
import com.anishan.dome.domain.vo.EnrollCourse;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author anishan
* @description 针对表【student_course(选课表)】的数据库操作Mapper
* @createDate 2024-12-23 11:02:13
* @Entity com.aninshan.dome.domain.entity.StudentCourse
*/
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

    void deleteBatchInSchoolYear(@Param("list") List<StudentCourse> courses, @Param("year") int year);

    List<EnrollCourse> selectAvailable(@Param("ew") Wrapper<TeacherCourse> wrapper, @Param("offset") Long offset, @Param("limit") Long limit);
    Long countAvailable(@Param("schoolYear") int schoolYear);

    List<EnrollCourse> selectByUser(@Param("userId") Long userId, @Param("schoolYear") int schoolYear);
}




