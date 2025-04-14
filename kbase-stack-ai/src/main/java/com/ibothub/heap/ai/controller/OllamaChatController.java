/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.controller;

import jakarta.annotation.Resource;
import java.util.Map;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/14 14:30
 */
@RestController
@RequestMapping("/api/chat/ollama")
public class OllamaChatController {

  @Resource
  private OllamaChatModel ollamaChatModel;

  @GetMapping("/ai/generate")
  public ResponseEntity<Map<String, String>> generate(
      @RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
    return ResponseEntity.ok(Map.of("generation", ollamaChatModel.call(message)));
  }

  @GetMapping("/ai/generateStream")
  public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
    Prompt prompt = new Prompt(new UserMessage(message));
    return ollamaChatModel.stream(prompt);
  }
}
