package com.ibothub.heap.minio.web.controller;

import com.ibothub.heap.base.model.vo.ResponseEntity;
import com.ibothub.heap.minio.service.MinioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.Bucket;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/6/11 14:28
 */
@Api(tags = "Minio Restful Api")
@RestController
@RequestMapping("api")
public class MinioController {

    @Resource
    MinioService minioService;

    @ApiOperation("创建bucket")
    @PostMapping("bucket")
    public ResponseEntity createBucket(String bucketName){
        minioService.createBucket(bucketName);
        return ResponseEntity.ok();
    }

    @ApiOperation("删除bucket")
    @DeleteMapping("bucket")
    public ResponseEntity deleteBucket(String bucketName){
        minioService.deleteBucket(bucketName);
        return ResponseEntity.ok();
    }

    @ApiOperation("上传文件")
    @PostMapping("upload")
    public ResponseEntity<List<Bucket>> upload(String bucketName, MultipartFile file) throws IOException {
        minioService.upload(bucketName, file);
        return ResponseEntity.ok();
    }

    @ApiOperation("下载文件")
    @GetMapping("download")
    public org.springframework.http.ResponseEntity<byte[]> upload(String bucketName, String key) throws IOException {
        return minioService.download(bucketName, key);
    }

}
