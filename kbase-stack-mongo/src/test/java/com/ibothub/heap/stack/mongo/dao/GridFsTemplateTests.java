package com.ibothub.heap.stack.mongo.dao;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/4/28 11:57
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class GridFsTemplateTests {

    @Resource
    private GridFsTemplate gridFsTemplate;

    @Resource
    private GridFSBucket gridFSBucket;

    @Test
    public void testUpload() throws IOException {
        File file = ResourceUtils.getFile("classpath:HelloWorld2.txt");
        FileInputStream in = new FileInputStream(file);


        gridFsTemplate.store(in, file.getName());
    }

    @Test
    public void testQuery() throws IOException {
        Query query = new Query();
        query.addCriteria(GridFsCriteria.whereFilename().is("HelloWorld2.txt"));
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);

        if (gridFSFile!=null){
            System.out.println(gridFSFile.getFilename());
            System.out.println(gridFSFile.getChunkSize());
            System.out.println(gridFSFile.getUploadDate());
            System.out.println("=================================");
            System.out.println(gridFSFile.toString());
            System.out.println("=================================");
            System.out.println(gridFSBucket);


            // 打开下载流
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            // 获取文件资源
            GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
            String result = IOUtils.toString(gridFsResource.getInputStream(), StandardCharsets.UTF_8);
            System.out.println("=================================");
            System.out.println(result);
            System.out.println("=================================");
            // 关闭下载流
            gridFSDownloadStream.close();


        }



    }
}
