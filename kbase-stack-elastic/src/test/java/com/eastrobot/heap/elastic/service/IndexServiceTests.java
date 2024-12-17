package com.eastrobot.heap.elastic.service;

import com.eastrobot.heap.elastic.config.ElasticConstants;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/4/13 19:52
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class IndexServiceTests {

    /**
     *
     */
    private final static String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Resource
    RestHighLevelClient restHighLevelClient;

    @Test
    public void testInsert() throws IOException {
        String url = "http://news.baidu.com/ent";

//        System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "fatal");

        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);


        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        // 启用JS解释器，默认为true
        webClient.getOptions().setJavaScriptEnabled(true);
        // 禁用css支持
        webClient.getOptions().setCssEnabled(false);
        // js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        // 设置连接超时时间
        webClient.getOptions().setTimeout(10 * 1000);
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(10 * 1000);               // 等待js后台执行30秒


//        System.out.println(page.asXml());

        Document doc = Jsoup.parse(page.asXml());
        Elements elements = doc.select(".ulist li a");

//        System.out.println(elements.html());
        BulkRequest bulkRequest = new BulkRequest();

        for (Element element : elements) {
            String text = element.text();
            String href = element.attr("href");

            String body = Jsoup.connect(href)
                    .execute()
                    .parse()
                    .select("[class^=index-module_articleWrap]")
                    .text();

            XContentBuilder xContentBuilder = JsonXContent
                    .contentBuilder()
                    .startObject()
                    .field("title", text)
                    .field("content", body)
                    .field("type", "娱乐新闻")
                    .field("website", href)
                    .field("version", "V1.0")
                    .field("creator", "占钊")
                    .field("createDate", DateFormatUtils.format(Calendar.getInstance().getTime(), DEFAULT_DATETIME_PATTERN))
                    .field("modifier", "占钊")
                    .field("modifyDate", DateFormatUtils.format(Calendar.getInstance().getTime(), DEFAULT_DATETIME_PATTERN))
                    .endObject();

            IndexRequest request = new IndexRequest(ElasticConstants.INDEX_NAME);
            request.source(xContentBuilder);
            bulkRequest.add(request);
        }


        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        for (BulkItemResponse item : bulkResponse.getItems()) {
            System.out.println(item.getFailureMessage());
        }

        webClient.close();

    }

    @Test
    public void testDelete() throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest(ElasticConstants.INDEX_NAME);
        request.setQuery(QueryBuilders.matchAllQuery());
        restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
    }
}
