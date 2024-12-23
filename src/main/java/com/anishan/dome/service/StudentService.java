package com.anishan.dome.service;

import com.anishan.dome.domain.dto.StudentDto;
import com.anishan.dome.domain.dto.StudentQuery;
import com.anishan.dome.domain.entity.Student;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.domain.vo.StudentVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anishan
* @description 针对表【student(用户分表 学生表)】的数据库操作Service
* @createDate 2024-12-23 11:02:13
*/
public interface StudentService extends IService<Student> {

    PageResponse<StudentVo> queryVo(StudentQuery query);

    Boolean removeStudentByIds(List<Long> userId);

    Boolean saveStudent(StudentDto entity);

    Boolean updateStudent(StudentDto studentDto);
}
