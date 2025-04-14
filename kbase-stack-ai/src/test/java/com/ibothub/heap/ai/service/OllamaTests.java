/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.service;

import org.junit.jupiter.api.Test;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/11 15:51
 */
@SpringBootTest
public class OllamaTests {

  @Autowired
  private OllamaChatModel ollamaChatModel;

  @Test
  public void testChatModel() {
    String prompt = """
                你是一个精通中文和英文的翻译大师。如果我给你英文就翻译成中文，给你中文就翻译成英文。
                """;
    String message = """
                Ollama now supports tool calling with popular models such as Llama 3.1.
                This enables a model to answer a given prompt using tool(s) it knows about,
                making it possible for models to perform more complex tasks or interact with the outside world.
                """;

    String result = ollamaChatModel.call(prompt + ":" + message);

    System.out.println(result);
  }
}
