/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/5/7 15:04
 */
@Configuration
public class ElasticSearchConfig {

    @Value("${elasticsearch.host}")
    private String hostList;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        // 解析 hostList 配置信息。假如以后有多个，则需要用 , 分开
        String[] split = hostList.split(",");
        // 创建 HttpHost 数组，其中存放es主机和端口的配置信息
        HttpHost[] httpHostArray = new HttpHost[split.length];
        for (int i = 0; i < split.length; i++) {
            String item = split[i];
            httpHostArray[i] = new HttpHost(item.split(":")[0], Integer.parseInt(item.split(":")[1]), "http");
        }
        // 创建RestHighLevelClient客户端
        return new RestHighLevelClient(RestClient.builder(httpHostArray));
    }
}
