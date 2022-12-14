package com.rainy.module.web.service;

import com.rainy.module.web.entity.ProjectFile;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 项目文件表 服务类
 * </p>
 *
 * @author rainy
 * @since 2022-09-23 12:04:57
 */
public interface ProjectFileService extends IService<ProjectFile> {
    Boolean createBatch(MultiValueMap<String, MultipartFile> multiValueMap);
}
