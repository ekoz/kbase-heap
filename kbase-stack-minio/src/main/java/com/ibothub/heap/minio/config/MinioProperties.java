package com.ibothub.heap.minio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/5/25 18:11
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String serviceEndpoint;

    private String region;

    private String accessKey;

    private String secretKey;

    private String signerOverride;


}
