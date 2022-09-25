package com.rainy.module.bigdata.service;

import com.rainy.module.bigdata.config.HdfsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;

@Slf4j
public class HdfsService {

    private static final int BUFFER_SIZE = 4096;
    private static final boolean CLOSE = true;
    private static final String BASE_PATH = "/visualization";

    private static HdfsConfig config;
    private static FileSystem fileSystem;
    private static Configuration configuration;

    static {
        config = HdfsConfig.builder()
                .path("hdfs://master:9000/")
                .user("root")
                .build();
        Configuration configuration = new Configuration();
        configuration.setBoolean("dfs.client.use.datanode.hostname", true);
        configuration.set("fs.defaultFS", config.getPath());
        configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        try {
            // 获得FileSystem对象，指定使用root用户上传
            fileSystem = FileSystem.get(new URI(config.getPath()), configuration,
                    config.getUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件上传至hdfs
     *
     * @param file
     * @param destination
     * @return void
     **/
    // TODO 不能写入hdfs中
    public static String uploadFile(MultipartFile file, String destination) {
        try {
            String folderString = String.format("%s/%s", BASE_PATH, destination);
            Path folderPath = new Path(folderString);
            if (!exist(folderPath)) {
                mkdir(folderPath);
            }
            String fileString = String.format("%s/%s", folderString, file.getOriginalFilename());
            Path filePath = new Path(fileString);
            InputStream in = file.getInputStream();
            FSDataOutputStream out = fileSystem.create(filePath);
            IOUtils.copyBytes(in, out, BUFFER_SIZE, CLOSE);
            return String.format("%s/%s", config.getPath(), fileString);
        } catch (Exception e) {
            log.error(e.toString());
            return "";
        }
    }

    /**
     * 创建文件夹
     *
     * @param path
     **/
    private static void mkdir(Path path) throws Exception {
        fileSystem.mkdirs(path);
    }


    private static boolean exist(Path path) throws Exception {
        boolean isExist = false;
        return fileSystem.exists(path);
    }

}
