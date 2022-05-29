package com.ibothub.heap.minio.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/5/26 10:32
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class S3ClientTests {

    static String bucketName = "zhanzhao";

    @Resource
    S3Client s3Client;

    @Test
    public void testCreateBucket(){
        if (!existBucket(bucketName)) {

            CreateBucketResponse bucket = s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());

            System.out.println(bucket);
        } else {
            System.out.println(bucketName + " 已存在");
        }
    }

    /**
     * bucket 是否存在
     * @param bucketName
     * @return
     */
    private Boolean existBucket(String bucketName){
        try{
            s3Client.headBucket(HeadBucketRequest.builder().bucket(bucketName).build());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Test
    public void testHeadBucket(){
        System.out.println(existBucket(bucketName));

    }


    @Test
    public void testPutBucketLifecycle(){
//        https://docs.aws.amazon.com/AmazonS3/latest/userguide/lifecycle-configuration-examples.html
//        https://docs.min.io/docs/minio-bucket-lifecycle-guide.html
//        https://blog.csdn.net/fxtxz2/article/details/114027688


        PutBucketLifecycleConfigurationRequest request = PutBucketLifecycleConfigurationRequest
                .builder()
                .bucket(bucketName)
                .lifecycleConfiguration(
                        BucketLifecycleConfiguration
                                .builder()
                                .rules(
                                        LifecycleRule
                                                .builder()
                                                .id("BucketExpirationRule")
                                                .filter(LifecycleRuleFilter.builder().prefix("").build())
                                                .status(ExpirationStatus.ENABLED)
                                                .expiration(
                                                        LifecycleExpiration.
                                                                builder()
                                                                .days(1)
                                                                .build()
                                                ).build()
                                ).build()
                )
                .build();
        System.out.println(request.toString());
        s3Client.putBucketLifecycleConfiguration(request);
    }

    @Test
    public void testGetBucketPolicy(){
        GetBucketPolicyResponse bucketPolicy = s3Client.getBucketPolicy(GetBucketPolicyRequest.builder().bucket(bucketName).build());
        System.out.println(bucketPolicy);
    }

    @Test
    public void testListBuckets(){
        s3Client.listBuckets()
                .buckets().
                forEach(System.out::println);
    }

    @Test
    public void testDeleteBucket(){
        s3Client.deleteBucket(DeleteBucketRequest.builder().bucket(bucketName).build());
    }

    @Test
    public void testListObjects(){
        s3Client.listObjects(ListObjectsRequest.builder().bucket(bucketName).build())
            .contents()
            .forEach(s3Object -> {
                System.out.println(s3Object.key());
            });
    }


    @Test
    public void testUploadFile(){
        String key = UUID.randomUUID().toString();
        File file = new File("C:\\Users\\zhanzhao\\Pictures\\019.jpeg");

        PutObjectRequest putObjectRequest = PutObjectRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .expires(Instant.now().plus(3, ChronoUnit.MINUTES))
                .build();


        PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
        System.out.println(putObjectResponse);
    }

    @Test
    public void testGetObject() throws IOException {

        String key = "2ec63a7c-1397-4762-b3c8-332ff70f4ce8";

        GetObjectRequest getObjectRequest = GetObjectRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .responseExpires(Instant.now())
                .build();

        ResponseInputStream<GetObjectResponse> objectContent = s3Client.getObject(getObjectRequest);

        // expiry-date="Sun, 29 May 2022 00:00:00 GMT", rule-id="BucketExpirationRule"
        System.out.println(objectContent.response().expiration());
        // 2022-05-27T09:37:59Z
        System.out.println(objectContent.response().expires());

//        OutputStream out = new FileOutputStream("D://" + key + ".jpg");
//        IOUtils.copy(objectContent, out);
//        out.close();

    }
}
