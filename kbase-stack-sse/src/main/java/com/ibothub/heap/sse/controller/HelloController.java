/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.sse.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2024/12/11 14:08
 */
@RestController
@RequestMapping("/api/v1/sse")
public class HelloController {

  @GetMapping("/events")
  public Flux<ServerSentEvent<String>> getEvents() {
    return Flux.interval(Duration.ofSeconds(1))
        .map(sequence -> ServerSentEvent.<String>builder()
            .id(String.valueOf(sequence))
            .event("message")
            .data("Event #" + sequence + " at " + LocalDateTime.now())
            .build());
  }

}
