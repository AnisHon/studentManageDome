package com.anishan.dome.service.impl;

import com.anishan.dome.domain.entity.Course;
import com.anishan.dome.mapper.CourseMapper;
import com.anishan.dome.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author anishan
* @description 针对表【course(用户分表 辅导员表)】的数据库操作Service实现
* @createDate 2024-12-23 11:02:13
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService {

}




