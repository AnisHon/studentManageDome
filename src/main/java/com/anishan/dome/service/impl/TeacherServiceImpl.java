package com.anishan.dome.service.impl;

import com.anishan.dome.domain.entity.Teacher;
import com.anishan.dome.mapper.TeacherMapper;
import com.anishan.dome.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author anishan
* @description 针对表【teacher(用户分表 教师表)】的数据库操作Service实现
* @createDate 2024-12-23 11:02:13
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService {

}




