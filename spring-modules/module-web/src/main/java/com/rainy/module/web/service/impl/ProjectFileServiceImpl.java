package com.rainy.module.web.service.impl;

import com.rainy.module.bigdata.service.HdfsService;
import com.rainy.module.web.entity.ProjectFile;
import com.rainy.module.web.mapper.ProjectFileMapper;
import com.rainy.module.web.service.ProjectFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * <p>
 * 项目文件表 服务实现类
 * </p>
 *
 * @author rainy
 * @since 2022-09-23 12:04:57
 */
@Service
@Transactional
public class ProjectFileServiceImpl extends ServiceImpl<ProjectFileMapper, ProjectFile> implements ProjectFileService {


    @Override
    public Boolean createBatch(MultiValueMap<String, MultipartFile> multiValueMap) {
        UUID projectUUID = UUID.randomUUID();
        List<ProjectFile> projectFileList = new LinkedList<>();
        multiValueMap.forEach((key, values) -> {
            String path = String.format("%s/%s", projectUUID, key);
            values.forEach((value) -> {
                String fileUrl = HdfsService.uploadFile(value, path);
                String fileName = value.getOriginalFilename();
                assert fileName != null;
                String[] fileNameSplit = fileName.split("\\.");
                String fileExtension = fileNameSplit[fileNameSplit.length - 1];
                projectFileList.add(ProjectFile.builder()
                        .fileName(fileName)
                        .fileExtension(fileExtension)
                        .fileType(getFileTypeByParam(key))
                        .fileUrl(fileUrl)
                        .build());
            });
        });
        return this.saveBatch(projectFileList);
    }

    private Integer getFileTypeByParam(String param) {
        int flag = 0;
        switch (param) {
            case "count_files":
                flag = 1;
                break;
            case "relation_files":
                flag = 2;
                break;
            default:
        }
        return flag;
    }
}
