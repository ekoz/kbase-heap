/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.elastic.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
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
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/5/7 15:04
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.elasticsearch.client.reactive")
@EnableConfigurationProperties
@Slf4j
public class ElasticConfig  {

    /**
     * https or http
     */
    private static final String HTTPS = "https";
    private int maxConnPerRoute = 128;
    private int maxConnTotal = 128;
    private int maxRetryTimeout = 60000;

    @Value("${spring.data.elasticsearch.client.reactive.username}")
    private String username;
    @Value("${spring.data.elasticsearch.client.reactive.password}")
    private String password;

    @Bean
    public RestClient restClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        HttpHost host = new HttpHost("localhost", 9200);

      return RestClient.builder(host)
            .setHttpClientConfigCallback(new HttpClientConfigCallback() {

                @Override
                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                    httpClientBuilder.disableAuthCaching();
                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                }
            }).build();
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
