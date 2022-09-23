package com.rainy.common.core.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.rainy.common.core.entity.BaseEntity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
// TODO 常量地址未修改

public class CodeGenerator {

    private static final String AUTHOR = "rainy";

    private static final String JDBC_URL = "jdbc:mysql://localhost:13306/visualization?useUnicode=true&characterEncoding=UTF-8" +
            "&useSSL=false&zeroDateTimeBehavior=convertToNull&";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "123456";

    private static final String BASE_PACKAGE = "com.rainy.module.web";
    private static final String PACKAGE_ENTITY = "entity";
    private static final String PACKAGE_MAPPER = "mapper";
    private static final String PACKAGE_SERVICE = "service";
    private static final String PACKAGE_SERVICE_IMPL = "service.impl";
    private static final String PACKAGE_CONTROLLER = "controller";

    private static final String PARENT_DIR = System.getProperty("user.dir") + "/spring-modules/module-web/src/main/";
    private static final String JAVA_DIR = PARENT_DIR + "java/com/rainy/module/web/";
    private static final String XML_PATH = PARENT_DIR + "resources/mapper";
    private static final String ENTITY_PATH = JAVA_DIR + "entity";
    private static final String MAPPER_PATH = JAVA_DIR + "mapper";
    private static final String SERVICE_PATH = JAVA_DIR + "service";
    private static final String SERVICE_IMPL_PATH = JAVA_DIR + "service/impl";
    private static final String CONTROLLER_PATH = JAVA_DIR + "controller";

    public static void main(String[] args) {
        List<String> includes = new LinkedList<>();
        includes.add("project_file");
        includes.add("project");
        FastAutoGenerator.create(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)
                .globalConfig(builder -> builder
                        .author(AUTHOR)
                        .enableSwagger()
                        .disableOpenDir()
                        .dateType(DateType.TIME_PACK)
                        .commentDate("yyyy-MM-dd hh:mm:ss"))
                .packageConfig(builder -> builder
                        .parent(BASE_PACKAGE)
                        .entity(PACKAGE_ENTITY)
                        .mapper(PACKAGE_MAPPER)
                        .service(PACKAGE_SERVICE)
                        .serviceImpl(PACKAGE_SERVICE_IMPL)
                        .controller(PACKAGE_CONTROLLER)
                        .pathInfo(
                                new HashMap() {{
                                    put(OutputFile.entity, ENTITY_PATH);
                                    put(OutputFile.mapper, MAPPER_PATH);
                                    put(OutputFile.xml, XML_PATH);
                                    put(OutputFile.service, SERVICE_PATH);
                                    put(OutputFile.serviceImpl, SERVICE_IMPL_PATH);
                                    put(OutputFile.controller, CONTROLLER_PATH);
                                }}
                        )
                )
                .strategyConfig(builder -> builder
                        .addInclude(includes)
                        // Entity策略配置
                        .entityBuilder()
                        .superClass(BaseEntity.class)
                        .enableChainModel()
                        .enableLombok()
                        .enableTableFieldAnnotation()
                        .addSuperEntityColumns("create_time", "update_time", "is_delete")
                        .idType(IdType.AUTO)
                        // Mapper策略配置
                        .mapperBuilder()
                        .enableMapperAnnotation()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapper")
                        // Service策略配置
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        // Controller策略配置
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .build()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }


}

