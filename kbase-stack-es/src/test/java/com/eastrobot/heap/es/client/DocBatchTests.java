/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.es.client;

import com.eastrobot.heap.es.config.Constants;
import com.eastrobot.heap.es.entity.Notice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.LicenseClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.license.GetLicenseRequest;
import org.elasticsearch.client.license.GetLicenseResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
public class DocBatchTests {

    @Resource
    RestHighLevelClient restHighLevelClient;

    //==================================================================
    //==== 数据操作
    //==================================================================

    @Test
    public void testBatchInsert() throws IOException {

        BulkRequest bulkRequest = new BulkRequest();

//        new IndexRequest().index("notice1").source(XContentType.JSON, "title", "", "content", "");
        bulkRequest.add(new IndexRequest().index("notice1").id("1002").source(XContentType.JSON, "title", "76岁女王海伦·米伦载着范·迪塞尔飙车，《速激9》气场全开", "content", "76岁女王海伦·米伦载着范·迪塞尔飙车，《速激9》气场全开"));
        bulkRequest.add(new IndexRequest().index("notice1").id("1003").source(XContentType.JSON, "title", "别人眼中的“小丑”，却是她心目中的英雄", "content", "别人眼中的“小丑”，却是她心目中的英雄"));
        bulkRequest.add(new IndexRequest().index("notice1").id("1004").source(XContentType.JSON, "title", "刘以豪分享和沈月拍吻戏细节 真实情况竟是“不敢动”", "content", "刘以豪分享和沈月拍吻戏细节 真实情况竟是“不敢动”"));
        bulkRequest.add(new IndexRequest().index("notice1").id("1005").source(XContentType.JSON, "title", "古韵新风熔冶一炉 “魅力天府”亮相中国品牌日", "content", "古韵新风熔冶一炉 “魅力天府”亮相中国品牌日"));

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.getTook());
    }


    @Test
    public void testBatchDelete() throws IOException {

        BulkRequest bulkRequest = new BulkRequest();

        bulkRequest.add(new DeleteRequest().index("notice1").id("1002"));
        bulkRequest.add(new DeleteRequest().index("notice1").id("1003"));
        bulkRequest.add(new DeleteRequest().index("notice1").id("1004"));
        bulkRequest.add(new DeleteRequest().index("notice1").id("1005"));

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.getTook());
    }


    @Test
    public void testDeleteAll() throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest("notice1");
        request.setQuery(QueryBuilders.matchAllQuery());
        restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
    }



}
