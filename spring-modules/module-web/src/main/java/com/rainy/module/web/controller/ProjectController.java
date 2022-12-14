package com.rainy.module.web.controller;

import com.rainy.common.core.annotation.CustomResponse;
import com.rainy.module.web.entity.Project;
import com.rainy.module.web.service.ProjectFileService;
import com.rainy.module.web.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 项目表 前端控制器
 * </p>
 *
 * @author rainy
 * @since 2022-09-23 12:04:56
 */
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectFileService projectFileService;

    @CustomResponse
    @RequestMapping(method = RequestMethod.POST, path = "")
    public Long creat(HttpServletRequest request) {
        MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
        Map<String, String[]> parameterMap = params.getParameterMap();
        MultiValueMap<String, MultipartFile> multiFileMap = params.getMultiFileMap();
        projectFileService.createBatch(multiFileMap);
        Project project = Project.builder()
                .projectName(parameterMap.get("name")[0])
                .projectDescription(parameterMap.get("description")[0])
                .projectUserGroup(Long.parseLong(parameterMap.get("user_group")[0]))
                .projectStatus(Integer.parseInt(parameterMap.get("status")[0]))
                .build();
        return projectService.createOne(project);
    }
}
