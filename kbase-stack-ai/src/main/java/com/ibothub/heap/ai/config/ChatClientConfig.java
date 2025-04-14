/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.config;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/11 14:44
 */
@Configuration
public class ChatClientConfig {

  @Resource
  private ToolCallbackProvider bookToolCallbackProvider;

  /**
   * 配置ChatClient，注册系统指令和工具函数
   */
  @Bean
  public ChatClient chatClient(ChatClient.Builder builder)   {
      return builder
            .defaultSystem("你是一个图书管理助手，可以帮助用户查询图书信息。"   +
                              "你可以根据书名模糊查询、根据作者查询和根据分类查询图书。"   +
                              "回复时，请使用简洁友好的语言，并将图书信息整理为易读的格式。")
              // 注册工具方法
            .defaultTools(bookToolCallbackProvider)
            .build();
    }
}
