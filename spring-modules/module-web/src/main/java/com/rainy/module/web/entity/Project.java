package com.rainy.module.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rainy.common.core.entity.BaseEntity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目表
 * </p>
 *
 * @author rainy
 * @since 2022-09-23 12:04:56
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("project")
@Builder
@ApiModel(value = "Project对象", description = "项目表")
public class Project extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("项目ID")
    @TableId(value = "project_id", type = IdType.AUTO)
    private Long projectId;

    @ApiModelProperty("项目名称")
    @TableField("project_name")
    private String projectName;

    @ApiModelProperty("项目描述")
    @TableField("project_description")
    private String projectDescription;

    @ApiModelProperty("项目用户组")
    @TableField("project_user_group")
    private Long projectUserGroup;

    @ApiModelProperty("项目状态(0开发中 1测试中 2已发布)")
    @TableField("project_status")
    private Integer projectStatus;


}
