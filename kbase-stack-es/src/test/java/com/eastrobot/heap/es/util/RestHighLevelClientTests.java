/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.es.util;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.LicenseClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/5/7 15:16
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RestHighLevelClientTests {

    @Resource
    RestHighLevelClient restHighLevelClient;

    @Test
    public void testMapping(){
        LicenseClient license = restHighLevelClient.license();
        System.out.println(license.toString());
    }

    @Test
    public void testDelIndex() throws IOException {
        // 删除索引
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("notice");
        restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
    }
}
