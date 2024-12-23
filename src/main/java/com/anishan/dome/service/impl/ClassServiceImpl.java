package com.anishan.dome.service.impl;

import com.anishan.dome.domain.dto.ClazzQuery;
import com.anishan.dome.domain.entity.Clazz;
import com.anishan.dome.domain.vo.ClassVo;
import com.anishan.dome.domain.vo.PageResponse;
import com.anishan.dome.mapper.ClassMapper;
import com.anishan.dome.service.ClassService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author anishan
* @description 针对表【class(班级表)】的数据库操作Service实现
* @createDate 2024-12-23 11:02:13
*/
@Service
@RequiredArgsConstructor
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Clazz>
    implements ClassService {

    private final ClassMapper classMapper;

    @Override
    public List<ClassVo> all() {
        return classMapper.selectClassVoAll();
    }

    @Override
    public PageResponse<ClassVo> listVo(ClazzQuery query) {
        Page<Clazz> page = query.queryPage();
        LambdaQueryWrapper<Clazz> wrapper = query.queryWrapper();
        List<ClassVo> classVos = classMapper.selectClassVo(page, wrapper);
        return PageResponse.build(page.getTotal(), classVos);
    }
}




