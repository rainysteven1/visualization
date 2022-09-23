package com.rainy.module.web.mapper;

import com.rainy.module.web.entity.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 项目表 Mapper 接口
 * </p>
 *
 * @author rainy
 * @since 2022-09-23 12:04:56
 */
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

}
