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
 * 项目文件表
 * </p>
 *
 * @author rainy
 * @since 2022-09-23 12:04:57
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@TableName("project_file")
@ApiModel(value = "ProjectFile对象", description = "项目文件表")
public class ProjectFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件ID")
    @TableId(value = "file_id", type = IdType.AUTO)
    private Long fileId;

    @ApiModelProperty("文件名称")
    @TableField("file_name")
    private String fileName;

    @ApiModelProperty("文件后缀")
    @TableField("file_extension")
    private String fileExtension;

    @ApiModelProperty("文件类型 (0项目文件 1统计文件 2依赖文件)")
    @TableField("file_type")
    private Integer fileType;

    @ApiModelProperty("文件URL")
    @TableField("file_url")
    private String fileUrl;


}
