package com.ibothub.heap.minio.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/5/26 10:29
 */
public interface MinioService {

    /**
     * 判断 bucket 是否存在
     * @param bucketName
     * @return
     */
    Boolean existBucket(String bucketName);

    /**
     * 获取 bucket list
     * @return
     */
    List<Bucket> listBucket();

    void createBucket(String bucketName);

    void deleteBucket(String bucketName);

    /* *********************************************************/
    /* *********************************************************/

    /**
     * 文件上传
     * @param bucketName
     * @param filePath
     */
    void upload(String bucketName, String filePath);

    void upload(String bucketName, MultipartFile file) throws IOException;

    /**
     * 文件下载
     * @param bucketName
     * @param key
     * @return
     */
    ResponseEntity<byte[]> download(String bucketName, String key) throws IOException;


    /**
     * 根据指定的 bucketName 遍历
     * @param bucketName
     * @return
     */
    List<S3Object> listObjects(String bucketName);

}
