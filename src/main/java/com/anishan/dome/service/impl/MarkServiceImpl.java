package com.anishan.dome.service.impl;

import com.anishan.dome.domain.dto.MarkDto;
import com.anishan.dome.domain.entity.Mark;
import com.anishan.dome.domain.vo.MarkVo;
import com.anishan.dome.mapper.MarkMapper;
import com.anishan.dome.service.MarkService;
import com.anishan.dome.utils.AuthUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
* @author anishan
* @description 针对表【mark(标记表)】的数据库操作Service实现
* @createDate 2024-12-23 11:18:31
*/
@Service
@RequiredArgsConstructor
public class MarkServiceImpl extends ServiceImpl<MarkMapper, Mark>
    implements MarkService {

    private final MarkMapper markMapper;

    @Override
    public void mark(MarkDto mark) {
        Long userId = AuthUtils.getUserId();
        Mark markObj = new Mark()
                .setInstructorId(userId)
                .setStudentId(mark.getStudentId());
        this.save(markObj);
    }

    @Override
    public List<MarkVo> listMarked() {
        Long userId = AuthUtils.getUserId();
        return markMapper.listAllByInstructorId(userId);
    }

    @Override
    public void updateMark(MarkDto mark) {
        Long userId = AuthUtils.getUserId();
        Mark markObj = new Mark()
                .setInstructorId(userId)
                .setStudentId(mark.getStudentId());
        this.update(markObj,
                new LambdaQueryWrapper<Mark>()
                        .eq(Mark::getStudentId, mark.getStudentId())
                        .eq(Mark::getInstructorId, userId)
        );

    }


    @Override
    @Transactional
    public void deleteBatch(List<Long> studentId) {
        Long userId = AuthUtils.getUserId();
        List<Mark> marks = studentId
                .stream()
                .map(x -> new Mark().setStudentId(x).setInstructorId(userId))
                .collect(Collectors.toList());
        markMapper.deleteByInstructorIdAndStudentId(marks);
    }


}




