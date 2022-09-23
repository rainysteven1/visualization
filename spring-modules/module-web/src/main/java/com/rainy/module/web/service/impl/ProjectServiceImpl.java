package com.rainy.module.web.service.impl;

import com.rainy.module.web.entity.Project;
import com.rainy.module.web.mapper.ProjectMapper;
import com.rainy.module.web.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 *
 * @author rainy
 * @since 2022-09-23 12:04:56
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

}
