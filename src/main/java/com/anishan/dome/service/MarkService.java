package com.anishan.dome.service;

import com.anishan.dome.domain.dto.MarkDto;
import com.anishan.dome.domain.entity.Mark;
import com.anishan.dome.domain.vo.MarkVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anishan
* @description 针对表【mark(标记表)】的数据库操作Service
* @createDate 2024-12-23 11:18:31
*/
public interface MarkService extends IService<Mark> {

    void mark(MarkDto studentId);


    List<MarkVo> listMarked();

    void updateMark(MarkDto mark);

    void deleteBatch(List<Long> userId);
}
