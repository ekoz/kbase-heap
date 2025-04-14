/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/11 14:35
 */
@SpringBootTest
public class BookServiceTests {

  @Resource
  BookService bookService;

  @Test
  public void testFindByTitle() {
    bookService.findByTitle("Java%")
        .forEach(System.out::println);
  }
}
