/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.elastic.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRestClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/5/7 15:04
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.elasticsearch.client.reactive")
@EnableConfigurationProperties(ReactiveElasticsearchRestClientProperties.class)
@Slf4j
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration  {

    /**
     * https or http
     */
    private static final String HTTPS = "https";
    private int maxConnPerRoute = 128;
    private int maxConnTotal = 128;
    private int maxRetryTimeout = 60000;

    @Resource
    ReactiveElasticsearchRestClientProperties properties;

    @SuppressWarnings("NullableProblems")
    @Override
    public RestHighLevelClient elasticsearchClient() {
        RestClientBuilder builder = RestClient.builder(properties.getEndpoints()
                .stream()
                .map(endpoint -> HTTPS + "://" + endpoint)
                .map(HttpHost::create)
                .toArray(HttpHost[]::new));

        // elastic http 连接池设置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            setSSLContext(httpClientBuilder);
            setCredentialsProvider(httpClientBuilder, properties);
            return httpClientBuilder.setMaxConnPerRoute(maxConnPerRoute).setMaxConnTotal(maxConnTotal);
        })
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setConnectTimeout((int)properties.getConnectionTimeout().toMillis())
                        .setSocketTimeout((int)properties.getSocketTimeout().toMillis()));

        return new RestHighLevelClient(builder);
    }

    /**
     * 设置 elastic basic 登录验证
     * @param builder
     * @param properties
     */
    private void setCredentialsProvider(HttpAsyncClientBuilder builder, ReactiveElasticsearchRestClientProperties properties) {
        if (StringUtils.isEmpty(properties.getUsername()) || StringUtils.isEmpty(properties.getPassword())) {
            log.warn("elastic 没有 用户名({})或者密码({})", properties.getUsername(), properties.getPassword());
        } else {
            // 配置 用户名密码认证
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword());
            provider.setCredentials(AuthScope.ANY, credentials);
            builder.setDefaultCredentialsProvider(provider);
        }
    }

    /**
     * 设置 ssl
     * @param clientBuilder
     */
    private void setSSLContext(HttpAsyncClientBuilder clientBuilder) {
        SSLContext sslContext;
        try {
            // 设置信任所有域
            SSLContextBuilder builder = SSLContexts.custom().loadTrustMaterial(TrustAllStrategy.INSTANCE);
            sslContext = builder.build();
            clientBuilder.setSSLContext(sslContext)
                    // 设置不做hostname过滤
                    .setSSLHostnameVerifier((host, session) -> host.equalsIgnoreCase(session.getPeerHost()));
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
