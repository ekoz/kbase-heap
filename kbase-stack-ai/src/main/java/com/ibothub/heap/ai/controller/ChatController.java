/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.controller;

import com.ibothub.heap.ai.model.vo.req.ChatRequest;
import com.ibothub.heap.ai.model.vo.resp.ChatResponse;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/11 14:45
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {

  @Resource
  private ChatClient chatClient;

  /**
    * 处理聊天请求，使用AI和MCP工具进行响应
    *
    *   @param   request 聊天请求
    *   @return   包含AI回复的响应
    */
  @PostMapping
  public   ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request)   {
          try   {
              // 创建用户消息
            String userMessage = request.getMessage();

              // 使用流式API调用聊天
            String content = chatClient.prompt()
                            .user(userMessage)
                            .call()
                            .content();

              return ResponseEntity.ok(new ChatResponse(content));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ChatResponse("处理请求时出错: "   + e.getMessage()));
        }
    }

}
