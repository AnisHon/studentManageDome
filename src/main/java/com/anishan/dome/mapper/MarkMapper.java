package com.anishan.dome.mapper;

import com.anishan.dome.domain.entity.Mark;
import com.anishan.dome.domain.vo.MarkVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author anishan
* @description 针对表【mark(标记表)】的数据库操作Mapper
* @createDate 2024-12-23 11:18:31
* @Entity com.aninshan.dome.domain.entity.Mark
*/
public interface MarkMapper extends BaseMapper<Mark> {

    void deleteByInstructorIdAndStudentId(@Param("list")List<Mark> marks);

    List<MarkVo> listAllByInstructorId(@Param("instructorId") Long instructorId);

    void updateTagByStudentIdAndInstructorId(@Param("studentId") Long studentId, @Param("instructorId") Long instructorId, @Param("tag") String tag);
}




