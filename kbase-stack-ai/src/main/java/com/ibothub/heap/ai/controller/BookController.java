/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.controller;

import com.ibothub.heap.ai.dao.BookRepository;
import com.ibothub.heap.ai.model.entity.Book;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/10 17:53
 */
@RestController
@RequestMapping("/api/book")
public class BookController {

  @Resource
  BookRepository bookRepository;

  @PostMapping("findAll")
  public ResponseEntity<Iterable<Book>> findAll(){
    return ResponseEntity.ok(bookRepository.findAll());
  }
}
