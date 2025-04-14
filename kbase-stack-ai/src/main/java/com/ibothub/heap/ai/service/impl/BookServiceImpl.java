/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.service.impl;

import com.ibothub.heap.ai.dao.BookRepository;
import com.ibothub.heap.ai.model.entity.Book;
import com.ibothub.heap.ai.service.BookService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/11 14:26
 */
@Service
public class BookServiceImpl implements BookService {

  @Resource
  BookRepository bookRepository;

  @Override
  @Tool(name = "findByTitle", description = "根据书名模糊查询图书")
  public List<Book> findByTitle(@ToolParam(description = "书名")String title) {
    return bookRepository.findByTitleLike("%" + title + "%");
  }

  @Override
  @Tool(name = "findByAuthor", description = "根据作者精确查询图书")
  public List<Book> findByAuthor(@ToolParam(description = "作者")String author) {
    return bookRepository.findByAuthor(author);
  }

  @Override
  @Tool(name = "findByCategory", description = "根据图书分类精确查询图书")
  public List<Book> findByCategory(@ToolParam(description = "图书分类") String category) {
    return bookRepository.findByCategory(category);
  }
}
