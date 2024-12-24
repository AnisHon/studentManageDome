package com.anishan.dome.service;

import com.anishan.dome.domain.dto.QueryEnrollCourse;
import com.anishan.dome.domain.dto.ScoreQuery;
import com.anishan.dome.domain.dto.StatisticFrom;
import com.anishan.dome.domain.entity.StudentCourse;
import com.anishan.dome.domain.vo.EnrollCourse;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.domain.vo.ScoreVo;
import com.anishan.dome.domain.vo.StatisticResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author anishan
* @description 针对表【student_course(选课表)】的数据库操作Service
* @createDate 2024-12-23 11:02:13
*/
public interface StudentCourseService extends IService<StudentCourse> {

    boolean enroll(Long userId, List<Long> teachId);

    @Transactional
    void drop(Long userId, List<Long> teachId);

    PageResponse<EnrollCourse> listAvailable(QueryEnrollCourse query);

    List<EnrollCourse> listEnrolled(Long userId);

    PageResponse<ScoreVo> queryScore(ScoreQuery query, Boolean asc);

    void removeBatch(List<StudentCourse> studentCourse);

    PageResponse<StatisticResult> statistic(StatisticFrom form);
}
