package com.ibothub.heap.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://docs.min.io/docs/how-to-use-aws-sdk-for-java-with-minio-server.html
 * https://docs.aws.amazon.com/zh_cn/sdk-for-java/v1/developer-guide/examples-s3.html
 * https://stackoverflow.com/questions/57433717/which-jar-to-use-for-dynamodb-with-java
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/5/25 17:31
 */
@SpringBootApplication
public class MinioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinioApplication.class, args);
    }
}
