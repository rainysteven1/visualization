package com.rainy.module.web.service.impl;

import com.rainy.module.web.entity.ProjectStructureFile;
import com.rainy.module.web.mapper.ProjectStructureFileMapper;
import com.rainy.module.web.service.ProjectStructureFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目结构文件表 服务实现类
 * </p>
 *
 * @author rainy
 * @since 2022-09-23 10:02:03
 */
@Service
public class ProjectStructureFileServiceImpl extends ServiceImpl<ProjectStructureFileMapper, ProjectStructureFile> implements ProjectStructureFileService {
}
