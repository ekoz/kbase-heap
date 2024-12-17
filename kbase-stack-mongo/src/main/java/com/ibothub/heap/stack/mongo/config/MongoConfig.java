package com.ibothub.heap.stack.mongo.config;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import jakarta.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/5/6 13:48
 */
@Configuration
public class MongoConfig {

    @Resource
    MongoTemplate mongoTemplate;

    @Bean
    public GridFSBucket getGridFSBucket(){
        return GridFSBuckets.create(mongoTemplate.getDb());
    }
}
