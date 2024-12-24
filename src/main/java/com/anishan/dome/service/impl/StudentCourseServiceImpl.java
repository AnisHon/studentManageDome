package com.anishan.dome.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.anishan.dome.domain.dto.QueryEnrollCourse;
import com.anishan.dome.domain.dto.ScoreQuery;
import com.anishan.dome.domain.dto.StatisticFrom;
import com.anishan.dome.domain.entity.Course;
import com.anishan.dome.domain.entity.StudentCourse;
import com.anishan.dome.domain.entity.TeacherCourse;
import com.anishan.dome.domain.vo.EnrollCourse;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.domain.vo.ScoreVo;
import com.anishan.dome.domain.vo.StatisticResult;
import com.anishan.dome.exception.BusinessException;
import com.anishan.dome.mapper.StudentCourseMapper;
import com.anishan.dome.service.StudentCourseService;
import com.anishan.dome.utils.SchoolYearUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author anishan
* @description 针对表【student_course(选课表)】的数据库操作Service实现
* @createDate 2024-12-23 11:02:13
*/
@Service
@RequiredArgsConstructor
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourseMapper, StudentCourse>
    implements StudentCourseService {

    private final StudentCourseMapper studentCourseMapper;
    private final SchoolYearUtil schoolYearUtil;

    @Override
    @Transactional
    public boolean enroll(Long userId, List<Long> teachId) {
        if (CollectionUtil.isEmpty(teachId)) {
            return false;
        }

        int schoolYear = schoolYearUtil.getSchoolYear();

        List<StudentCourse> studentCourses = teachId
                .stream()
                .map(id -> new StudentCourse().setTeachId(id).setUserId(userId))
                .collect(Collectors.toList());

        List<Long> courseId = Db
                .listByIds(teachId, TeacherCourse.class)
                .stream()
                .map(TeacherCourse::getCourseId)
                .collect(Collectors.toList());

        List<Course> courses = Db.listByIds(courseId, Course.class);

        courses.forEach(course -> {
            if (course.getSchoolYear() != schoolYear) {
                throw new BusinessException("无效选课！");
            }
        });


        return this.saveBatch(studentCourses);
    }

    @Transactional
    @Override
    public void drop(Long userId, List<Long> teachId) {
        if (CollectionUtil.isEmpty(teachId)) {
            return;
        }
        int schoolYear = schoolYearUtil.getSchoolYear();
        List<StudentCourse> studentCourses = teachId
                .stream()
                .map(id -> new StudentCourse().setTeachId(id).setUserId(userId))
                .collect(Collectors.toList());


        studentCourseMapper.deleteBatchInSchoolYear(studentCourses, schoolYear);
    }

    @Override
    public PageResponse<EnrollCourse> listAvailable(QueryEnrollCourse query) {
        int schoolYear = schoolYearUtil.getSchoolYear();

        QueryWrapper<TeacherCourse> wrapper = query.plainQueryWrapper();

        wrapper.eq("c.school_year", schoolYear);

        Long pageNum = query.getPageNum();
        Long pageSize = query.getPageSize();

        Long offset = (pageNum - 1) * pageSize;

        List<EnrollCourse> enrollCourses = studentCourseMapper.selectAvailable(wrapper, offset, pageSize);

        Long total = studentCourseMapper.countAvailable(schoolYear);

        return PageResponse.build(total, enrollCourses);
    }

    @Override
    public List<EnrollCourse> listEnrolled(Long userId) {
        int schoolYear = schoolYearUtil.getSchoolYear();
        return studentCourseMapper.selectByUser(userId, schoolYear);
    }

    @Override
    public PageResponse<ScoreVo> queryScore(ScoreQuery query, Boolean asc) {
        Page<StudentCourse> page = query.queryPage();
        LambdaQueryWrapper<StudentCourse> wrapper = query.queryWrapper();

        if (asc != null) {
            OrderItem orderItem = new OrderItem();
            if (asc.equals(Boolean.TRUE)) {
                orderItem.setAsc(Boolean.TRUE);
            } else if (asc.equals(Boolean.FALSE)) {
                orderItem.setAsc(Boolean.FALSE);

            }
            orderItem.setColumn("sc.score");
            page.addOrder(orderItem);
        }

        page.setSearchCount(false);
        List<ScoreVo> scoreVos = studentCourseMapper.selectScoreVos(page, wrapper);

        Long l = studentCourseMapper.countScores(query.getSchoolYear());

        return PageResponse.build(l, scoreVos);
    }

    @Override
    public void removeBatch(List<StudentCourse> studentCourse) {
        studentCourseMapper.removeScore(studentCourse);
    }

    @Override
    public PageResponse<StatisticResult> statistic(StatisticFrom form) {

        LambdaQueryWrapper<StudentCourse> wrapper = form.queryWrapper();
        Page<StudentCourse> page = form.queryPage();
        page.setSearchCount(false);
        List<StatisticResult> statistic = studentCourseMapper.statistic(wrapper, page);
        Long total = studentCourseMapper.count(wrapper);

        return PageResponse.build(total, statistic);
    }
}




