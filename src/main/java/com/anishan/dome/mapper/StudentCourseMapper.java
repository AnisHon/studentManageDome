package com.anishan.dome.mapper;

import com.anishan.dome.domain.dto.StatisticFrom;
import com.anishan.dome.domain.entity.StudentCourse;
import com.anishan.dome.domain.entity.TeacherCourse;
import com.anishan.dome.domain.vo.EnrollCourse;
import com.anishan.dome.domain.vo.ScoreVo;
import com.anishan.dome.domain.vo.StatisticResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    List<EnrollCourse> selectAvailable(@Param("page") Page<TeacherCourse> page, @Param("ew") Wrapper<TeacherCourse> wrapper);
    Long countAvailable(@Param("ew") Wrapper<TeacherCourse> wrapper);

    List<EnrollCourse> selectByUser(@Param("userId") Long userId, @Param("schoolYear") int schoolYear);

    List<ScoreVo> selectScoreVos(@Param("page")Page<?> page, @Param("ew") Wrapper<?> wrapper);

    Long countScores(@Param("schoolYear") Integer schoolYear);

    void removeScore(@Param("list") List<StudentCourse> studentCourse);

    List<StatisticResult> statistic(@Param("ew") LambdaQueryWrapper<StudentCourse> wrapper, @Param("page") Page<StudentCourse> page);

    Long count(@Param("ew") LambdaQueryWrapper<StudentCourse> wrapper);
}




