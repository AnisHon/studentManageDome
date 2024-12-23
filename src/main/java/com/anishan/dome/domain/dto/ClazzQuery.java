package com.anishan.dome.domain.dto;

import cn.hutool.core.util.StrUtil;
import com.anishan.dome.domain.entity.Clazz;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 班级表
 * @TableName class
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("班级")
public class ClazzQuery extends BaseQueryParam<Clazz> {

    @ApiModelProperty("班级id")
    private Long classId;

    @ApiModelProperty("专业ID")
    private Long majorId;

    @ApiModelProperty("班级姓名")
    private String className;

    @Override
    public LambdaQueryWrapper<Clazz> queryWrapper() {

        QueryWrapper<Clazz> clazzQueryWrapper = new QueryWrapper<Clazz>()
                .eq(classId != null, "class_id", classId)
                .eq(majorId != null, "major.major_id", majorId)
                .eq(!StrUtil.isEmpty(className), "class_name", className);
        return clazzQueryWrapper.lambda();
    }
}