package com.rainy.module.bigdata.service;

import com.rainy.module.bigdata.config.SparkConfig;
import entity.ProjectFileScala;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AnalysisProject;

import java.util.List;
import java.util.Map;

@Service
public class SparkService {
    @Autowired
    private SparkSession sparkSession;

    public void createAnalysisProjectTask(Map<String, List<ProjectFileScala>> projectFileScalaMap) {
        AnalysisProject.analysis(sparkSession, projectFileScalaMap);
    }
}
