package com.anishan.dome.domain.vo;

import com.anishan.dome.domain.entity.Clazz;
import com.anishan.dome.domain.entity.Major;
import com.anishan.dome.enumeration.Gender;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class StudentVo {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("工号")
    private String workNumber;

    @ApiModelProperty("专业ID")
    private Long majorId;

    @ApiModelProperty("班级id")
    private Long classId;

    @ApiModelProperty("班级名")
    private String className;

    @ApiModelProperty("姓名")
    private String username;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("性别 0 男 1女")
    private Gender gender;

    @ApiModelProperty("专业名称")
    private String majorName;

    @ApiModelProperty("学院名称")
    private String institution;


}
