/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.service;

import com.ibothub.heap.ai.model.entity.Book;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/11 14:24
 */
public interface BookService {

  List<Book> findByTitle(String title);

  /**
   * 根据作者查询
   * @param author
   * @return
   */
  List<Book> findByAuthor(String author);

  /**
   * 根据分类查询
   * @param category
   * @return
   */
  List<Book> findByCategory(String category);
}
