package com.ibothub.heap.minio.service.impl;

import com.google.common.collect.Maps;
import com.ibothub.heap.minio.service.MinioService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/5/26 10:30
 */
@Service
@Slf4j
public class MinioServiceImpl implements MinioService {

    @Resource
    S3Client s3Client;

    /**
     * 判断 bucket 是否存在
     *
     * @param bucketName
     * @return
     */
    @Override
    public Boolean existBucket(String bucketName) {
        try{
            s3Client.headBucket(HeadBucketRequest.builder().bucket(bucketName).build());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获取 bucket list
     *
     * @return
     */
    @Override
    public List<Bucket> listBucket() {
        return s3Client.listBuckets()
                .buckets();
    }

    @Override
    public void createBucket(String bucketName) {
        if (!existBucket(bucketName)) {
            s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
        } else {
            throw new IllegalArgumentException(bucketName + " 已存在");
        }
    }

    @Override
    public void deleteBucket(String bucketName) {
        s3Client.deleteBucket(DeleteBucketRequest.builder().bucket(bucketName).build());
    }

    /**
     * 文件上传
     *
     * @param bucketName
     * @param filePath
     */
    @Override
    public void upload(String bucketName, String filePath) {
        String key = UUID.randomUUID().toString();
        File file = new File(filePath);

        PutObjectRequest putObjectRequest = PutObjectRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
    }

    @Override
    public void upload(String bucketName, MultipartFile file) throws IOException {
        String key = UUID.randomUUID().toString();
        log.debug(key);

        Map<String, String> metadata = Maps.newHashMap();
        metadata.put("filename", StringEscapeUtils.escapeJava(file.getOriginalFilename()));

        PutObjectRequest putObjectRequest = PutObjectRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .metadata(metadata)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
    }

    /**
     * 文件下载
     *
     * @param bucketName
     * @param key
     * @return
     */
    @Override
    public ResponseEntity<byte[]> download(String bucketName, String key) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .responseExpires(Instant.now())
                .build();

        ResponseInputStream<GetObjectResponse> objectContent = s3Client.getObject(getObjectRequest);
        Map<String, String> metadata = objectContent.response().metadata();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(objectContent.response().contentType()));
        headers.setContentLength(objectContent.response().contentLength());
        String filename = StringEscapeUtils.unescapeJava(metadata.get("filename"));
        log.debug(filename);
///        headers.set("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(filename, StandardCharsets.UTF_8.name()));
        // content-disposition: attachment; filename*=UTF-8''%E6%A1%88%E4%BB%B6_%E5%BE%85%E8%BD%AC%E5%8F%91_2206111000552.pdf
        // 在 swagger 界面，这里显示的文件名是乱码，swagger 下载文件名也是乱码，但是在浏览器直接输入后，下载文件的地址是正确的
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(filename, StandardCharsets.UTF_8).build());
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(IOUtils.toByteArray(objectContent));
    }

    /**
     * 根据指定的 bucketName 遍历
     *
     * @param bucketName
     * @return
     */
    @Override
    public List<S3Object> listObjects(String bucketName) {
        return s3Client.listObjects(ListObjectsRequest.builder().bucket(bucketName).build())
                .contents();
    }
}
