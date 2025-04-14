/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.config;

import com.ibothub.heap.ai.service.BookService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/11 14:39
 */
@Configuration
public class McpServerConfig {

  /**
    * 注册工具回调提供者，将BookQueryService中的@Tool方法暴露为MCP工具
    *
    *  @param  bookService 图书服务
    *  @return  工具回调提供者
    */
  @Bean
  public  ToolCallbackProvider  bookToolCallbackProvider(BookService bookService)  {
       return  MethodToolCallbackProvider.builder()
                  .toolObjects(bookService)
                  .build();
   }
}
