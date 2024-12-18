/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.sse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2024/12/13 10:27
 */
@Slf4j
public class HelloServiceTests {

  static WebClient webClient = WebClient.create("http://localhost:8080");

  public static void main(String[] args) {
    func3();
  }

  private static void func3(){
    webClient
        .post()
        .uri("/api/v1/sse/events")
        .accept(MediaType.TEXT_EVENT_STREAM)
        .retrieve()
        .bodyToFlux(String.class)
        .transformDeferredContextual((stream, ctx) -> {
          return stream.handle((event, sink) -> {
            System.out.println("111" + event);
            sink.next("222" + event);
          });
        }).subscribe(System.out::println);

    try {
      Thread.sleep(60 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void func1(){
    webClient
        .post()
        .uri("/api/v1/sse/events")
        .accept(MediaType.TEXT_EVENT_STREAM)
        .retrieve()
        .bodyToFlux(String.class)
        .subscribe(System.out::println);

//    try {
//      Thread.sleep(60 * 1000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }

    log.info("end");
  }

  private static void func2(){
    Mono<List<String>> listMono = webClient
        .post()
        .uri("/api/v1/sse/events")
        .accept(MediaType.TEXT_EVENT_STREAM)
        .retrieve()
        .bodyToFlux(String.class)
        .collectList();

    System.out.println("end");
    listMono.block().forEach(System.out::println);
  }

  @Test
  public void readFile() throws IOException {
    String txt = FileUtils.readFileToString(ResourceUtils.getFile("classpath:data.json"),
        StandardCharsets.UTF_8);
    JSONObject jsonObject = JSON.parseObject(txt);
    JSONArray famousArr = jsonObject.getJSONArray("famous");
    famousArr.forEach(System.out::println);
  }
}
