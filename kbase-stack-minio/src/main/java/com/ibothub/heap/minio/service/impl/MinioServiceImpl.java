package com.ibothub.heap.minio.service.impl;

import com.ibothub.heap.minio.service.MinioService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/5/26 10:30
 */
@Service
public class MinioServiceImpl implements MinioService {

    @Resource
    S3Client s3Client;

}
