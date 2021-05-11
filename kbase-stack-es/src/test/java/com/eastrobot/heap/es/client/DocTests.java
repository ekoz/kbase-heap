/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.es.client;

import com.eastrobot.heap.es.entity.Notice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.LicenseClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.license.GetLicenseRequest;
import org.elasticsearch.client.license.GetLicenseResponse;
import org.elasticsearch.common.xcontent.XContentType;
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
public class DocTests {

    @Resource
    RestHighLevelClient restHighLevelClient;

    //==================================================================
    //==== 数据操作
    //==================================================================

    @Test
    public void testInsert() throws IOException {

        IndexRequest indexRequest = new IndexRequest("notice1");
        indexRequest.id("1001");

        Notice notice = Notice.builder()
                .title("你看那猴子“笑”那么开心，它真的高兴吗？")
                .content("《西游记》里孙悟空大闹天宫或者战胜妖怪时，总会开怀大笑：露出牙齿，嘴巴咧开，别提有多快乐了。")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        indexRequest.source(objectMapper.writeValueAsString(notice), XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse.getResult());
    }

    @Test
    public void testUpdate() throws IOException {

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("notice1")
                .id("1001")
                .doc(XContentType.JSON, "contentCN", "更新测试");

        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.getResult());
    }

    @Test
    public void testGet() throws IOException {

        GetRequest getRequest = new GetRequest();
        getRequest.index("notice1")
                .id("1001");

        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());
    }

    @Test
    public void testDelete() throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest("notice1");
        deleteRequest.id("1001");

        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.getResult());
    }



    @Test
    public void testLicense() throws IOException {
        LicenseClient license = restHighLevelClient.license();
        GetLicenseRequest getLicenseRequest = new GetLicenseRequest();
        GetLicenseResponse getLicenseResponse = license.getLicense(getLicenseRequest, RequestOptions.DEFAULT);
        System.out.println(getLicenseResponse.getLicenseDefinition());
    }

}
