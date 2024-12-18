/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.sse.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Lists;
import com.ibothub.heap.base.model.vo.ResponseEntity;
import com.ibothub.heap.sse.model.vo.req.MessageReq;
import com.ibothub.heap.sse.model.vo.resp.MessageResp;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2024/12/17 14:52
 */
@RestController
@RequestMapping("/api/v1/sse")
public class HelloController {

  List<String> famousList = Lists.newArrayList();

  private final ConcurrentHashMap<String, Sinks.Many<MessageResp>> sinks = new ConcurrentHashMap<>();

  @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<MessageResp>> stream(@RequestParam("token") String token) {
    Sinks.Many<MessageResp> sink = Sinks.many().multicast().directBestEffort();
    sinks.put(token, sink);

    return sink.asFlux().map(data ->
        ServerSentEvent.<MessageResp>builder()
            .data(data)
            .build());
  }

  @PostMapping("/send")
  public ResponseEntity<String> send(@RequestBody MessageReq req, @RequestHeader("Authorization") String token) {
    Sinks.Many<MessageResp> sink = sinks.get(token);
    if (sink != null) {
      MessageResp messageResp = new MessageResp();
      messageResp.setMessage(req.getMessage() + "，" + getFamous());
      sink.tryEmitNext(messageResp);
      return ResponseEntity.ok("");
    } else {
      return ResponseEntity.failure("");
    }
  }

  @PostConstruct
  public void init() throws IOException {
    String txt = FileUtils.readFileToString(ResourceUtils.getFile("classpath:data.json"),
        StandardCharsets.UTF_8);
    JSONObject jsonObject = JSON.parseObject(txt);
    JSONArray famousArr = jsonObject.getJSONArray("famous");
    famousArr.forEach(famous -> famousList.add(famous.toString()));
  }

  private String getFamous(){
    Random random = new Random();
    int num = random.nextInt(famousList.size());
    return famousList.get(num);
  }

}
