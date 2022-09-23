package com.rainy.module.bigdata.config;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class HdfsConfig {
    private String path;

    private String user;

}
