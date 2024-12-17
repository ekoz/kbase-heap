package com.ibothub.heap.minio.config;

import io.minio.MinioClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import jakarta.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/5/25 17:36
 */
@Configuration
@EnableConfigurationProperties(value = MinioProperties.class)
public class MinioConfig {

    @Resource
    MinioProperties properties;

    @Bean(value = "s3Client")
    S3Client s3Client() throws URISyntaxException {

        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(properties.getAccessKey(), properties.getSecretKey());



        return S3Client.builder()
                .endpointOverride(new URI(properties.getServiceEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .region(getDefaultRegion())
                .build();

    }

    @Bean(value = "minioClient")
    MinioClient minioClient() {

        return MinioClient.builder()
                .endpoint(properties.getServiceEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .region(getDefaultRegion().id())
                .build();

    }

    /**
     * 获取默认的时区
     * @return
     */
    private Region getDefaultRegion(){
        Region region = Region.CN_NORTH_1;
        if (StringUtils.isNotBlank(properties.getRegion())){
            region = Region.of(properties.getRegion());
        }
        return region;
    }

}
