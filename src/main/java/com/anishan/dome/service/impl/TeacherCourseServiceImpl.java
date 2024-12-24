package com.anishan.dome.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.anishan.dome.domain.entity.TeacherCourse;
import com.anishan.dome.domain.vo.EnrollCourse;
import com.anishan.dome.domain.vo.TeacherVo;
import com.anishan.dome.mapper.TeacherCourseMapper;
import com.anishan.dome.service.TeacherCourseService;
import com.anishan.dome.utils.AuthUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author anishan
* @description 针对表【teacher_course(任教表)】的数据库操作Service实现
* @createDate 2024-12-23 11:02:13
*/
@Service
@RequiredArgsConstructor
public class TeacherCourseServiceImpl extends ServiceImpl<TeacherCourseMapper, TeacherCourse>
    implements TeacherCourseService {


    private final TeacherCourseMapper teacherCourseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assign(Long courseId, List<Long> teacherId) {
        if (CollectionUtil.isEmpty(teacherId)) {
            return;
        }

        List<TeacherCourse> tcs = teacherId
                .stream()
                .map(x -> new TeacherCourse().setUserId(x).setCourseId(courseId))
                .collect(Collectors.toList());

        this.saveBatch(tcs);
    }

    @Override
    @Transactional
    public void reclaim(List<Long> teachId) {
        this.removeByIds(teachId);
    }

    @Override
    public List<EnrollCourse> getAssignedCourse(Integer year) {
        Long userId = AuthUtils.getUserId();
        return teacherCourseMapper.selectCourse(userId, year);
    }

    @Override
    public List<TeacherVo> getAssignedTeacher(Long courseId) {
        return teacherCourseMapper.selectTeacher(courseId);
    }
}




