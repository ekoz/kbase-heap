package com.ibothub.heap.minio.service;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.*;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/5/27 13:05
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MinioClientTests {

    static String bucketName = "zhanzhao-minio";

    @Resource
    MinioClient minioClient;

    @Test
    public void tesMakeBucket() throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {

        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        List<LifecycleRule> rules = Lists.newArrayList();
        rules.add(new LifecycleRule(
                Status.ENABLED,
                null,
                new Expiration((ZonedDateTime) null, 3, null),
                new RuleFilter(""),
                "rule2",
                null,
                null,
                null));

        LifecycleConfiguration lifecycleConfiguration = new LifecycleConfiguration(rules);

        minioClient.setBucketLifecycle(
                SetBucketLifecycleArgs.builder()
                        .bucket(bucketName)
                        .config(lifecycleConfiguration)
                        .build()
        );

    }

}
