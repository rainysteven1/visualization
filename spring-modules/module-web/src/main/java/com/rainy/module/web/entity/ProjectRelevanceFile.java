package com.rainy.module.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rainy.common.core.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目与项目文件关联表
 * </p>
 *
 * @author rainy
 * @since 2022-09-23 12:04:57
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("project_relevance_file")
@ApiModel(value = "ProjectRelevanceFile对象", description = "项目与项目文件关联表")
public class ProjectRelevanceFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关联ID")
    @TableId(value = "relevance_id", type = IdType.AUTO)
    private Long relevanceId;

    @ApiModelProperty("项目")
    @TableField("project")
    private Long project;

    @ApiModelProperty("项目结构文件")
    @TableField("project_structure_file")
    private Long projectStructureFile;

    @ApiModelProperty("项目统计文件")
    @TableField("project_count_file")
    private Long projectCountFile;

    @ApiModelProperty("项目依赖文件")
    @TableField("project_relation_file")
    private Long projectRelationFile;


}
