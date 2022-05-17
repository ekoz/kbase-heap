package com.eastrobot.heap.elastic.lang;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/4/13 19:58
 */
public class JsoupTests {

    @Test
    public void parse() throws IOException {

        String url = "http://news.baidu.com/ent";

//        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
//        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.WARNING);
//        java.util.logging.Logger.getLogger("org.apache.http").setLevel(Level.WARNING);

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

        for (Element element : elements) {
            System.out.println(element.text());
            System.out.println(element.attr("href"));

            String text = element.text();
            String href = element.attr("href");

            String body = Jsoup.connect(href)
                    .execute()
                    .parse()
                    .select("[class^=index-module_articleWrap]")
                    .text();

            System.out.println(body);

        }

        webClient.close();
    }
}
