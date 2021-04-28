package com.ibothub.heap.stack.mongo.dao;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.*;

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

    @Test
    public void testUpload() throws IOException {
        File file = ResourceUtils.getFile("classpath:HelloWorld.txt");
        FileInputStream in = new FileInputStream(file);

        gridFsTemplate.store(in, file.getName());
    }

    @Test
    public void testQuery(){
        Query query = new Query();
        query.addCriteria(GridFsCriteria.whereFilename().is("HelloWorld.txt"));
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        if (gridFSFile!=null){
            System.out.println(gridFSFile.getFilename());
            System.out.println(gridFSFile.getChunkSize());
            System.out.println(gridFSFile.getUploadDate());
        }

    }
}
