package com.anishan.dome.service.impl;

import com.anishan.dome.domain.entity.Student;
import com.anishan.dome.mapper.StudentMapper;
import com.anishan.dome.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author anishan
* @description 针对表【student(用户分表 学生表)】的数据库操作Service实现
* @createDate 2024-12-23 11:02:13
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService {

}




