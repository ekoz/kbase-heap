package com.eastrobot.heap.elastic.client;

import com.eastrobot.heap.elastic.config.Constants;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * match 与 match_phrase 的区别，match_phrase 是完全匹配
 * match query 搜索的时候，首先会解析查询字符串，进行分词，然后查询，而term query, 输入的查询内容是什么，就会按照什么去查询，并不会解析查询内容，对它分词
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/5/10 18:06
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DocQueryTests {

    @Resource
    RestHighLevelClient restHighLevelClient;


    /**
     * 查询所有
     * @throws IOException
     */
    @Test
    public void testQueryAll() throws IOException {

        SearchRequest request = new SearchRequest();
        request.indices("notice1");

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchAllQuery());

        request.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getTook());

        searchResponse.getHits().forEach(searchHit -> {
            System.out.println(searchHit.getSourceAsString());
        });


    }

    /**
     * 按条件查询
     * @throws IOException
     */
    @Test
    public void testQueryByCondition() throws IOException {

        SearchRequest request = new SearchRequest();
        request.indices("notice1");

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.termQuery("title", "别人"));

        request.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getTook());

        searchResponse.getHits().forEach(searchHit -> {
            System.out.println(searchHit.getSourceAsString());
        });


    }

    /**
     * 分页查询
     * @throws IOException
     */
    @Test
    public void testQueryByPage() throws IOException {

        SearchRequest request = new SearchRequest();
        request.indices("notice1");

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(3);

        request.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getTook());

        searchResponse.getHits().forEach(searchHit -> {
            System.out.println(searchHit.getSourceAsString());
        });
    }

    /**
     * 排序
     * @throws IOException
     */
    @Test
    public void testQueryByOrder() throws IOException {

        SearchRequest request = new SearchRequest();
        request.indices("notice1");

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.sort("contentCN", SortOrder.DESC);

        request.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getTook());

        searchResponse.getHits().forEach(searchHit -> {
            System.out.println(searchHit.getSourceAsString());
        });
    }

    /**
     * 字段过滤
     * @throws IOException
     */
    @Test
    public void testQueryByFieldFilter() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("notice1");

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchAllQuery());


        String[] includes = {"title"};
        String[] excludes = {"content", "contentCN", "desc"};
        searchSourceBuilder.fetchSource(includes, excludes);

        request.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getTook());

        searchResponse.getHits().forEach(searchHit -> {
            System.out.println(searchHit.getSourceAsString());
        });
    }


    /**
     * 组合查询
     * @throws IOException
     */
    @Test
    public void testQueryByBool() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("notice1");


        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.matchQuery("title", "英雄"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("content", "中国品牌"));

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(boolQueryBuilder);


        String[] includes = {"title", "content"};
        String[] excludes = {};
        searchSourceBuilder.fetchSource(includes, excludes);

        request.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getTook());

        searchResponse.getHits().forEach(searchHit -> {
            System.out.println(searchHit.getSourceAsString());
        });
    }


    /**
     * 模糊查询，隔一两个字也可以根据规则查出来，比如 中国，中国1 等等，用于英文比较合适
     * @throws IOException
     */
    @Test
    public void testQueryByFuzzy() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("notice1");


        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("title", "古韵新风熔冶一炉").fuzziness(Fuzziness.TWO);

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(fuzzyQueryBuilder);


        String[] includes = {"title", "content"};
        String[] excludes = {};
        searchSourceBuilder.fetchSource(includes, excludes);

        request.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getTook());

        searchResponse.getHits().forEach(searchHit -> {
            System.out.println(searchHit.getSourceAsString());
        });
    }


    /**
     * 高亮查询
     * @throws IOException
     */
    @Test
    public void testQueryByHighlight() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("notice1");


        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchQuery("title", "中国品牌"));


        String[] includes = {"title", "content"};
        String[] excludes = {};
        searchSourceBuilder.fetchSource(includes, excludes);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags(Constants.HL_PRE_TAG);
        highlightBuilder.postTags(Constants.HL_POST_TAG);
        highlightBuilder.field("title");
        highlightBuilder.field("content");

        searchSourceBuilder.highlighter(highlightBuilder);

        request.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getTook());

        searchResponse.getHits().forEach(searchHit -> {
            System.out.println(searchHit.getSourceAsString());
        });
    }

    /**
     * 聚合查询
     * @throws IOException
     */
    @Test
    public void testQueryByAggs() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("notice1");


        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchQuery("title", "中国品牌"));


        String[] includes = {"title", "content"};
        String[] excludes = {};
        searchSourceBuilder.fetchSource(includes, excludes);

        AggregationBuilder aggregationBuilder = AggregationBuilders.max("max").field("title");

        searchSourceBuilder.aggregation(aggregationBuilder);

        request.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getTook());

        searchResponse.getHits().forEach(searchHit -> {
            System.out.println(searchHit.getSourceAsString());
        });
    }

    /**
     * 分组查询
     * @throws IOException
     */
    @Test
    public void testQueryByAggsTerm() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("notice1");


        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchQuery("title", "中国品牌"));


        String[] includes = {"title", "content"};
        String[] excludes = {};
        searchSourceBuilder.fetchSource(includes, excludes);

        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("titleGroup").field("title");

        searchSourceBuilder.aggregation(aggregationBuilder);

        request.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getTook());

        searchResponse.getHits().forEach(searchHit -> {
            System.out.println(searchHit.getSourceAsString());
        });
    }


}
