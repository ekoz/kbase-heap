/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.dao;

import com.ibothub.heap.ai.model.entity.Book;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/10 17:35
 */
@Repository
public interface BookRepository  extends CrudRepository<Book, String> {

  List<Book> findByTitleLike(String title);

  List<Book> findByAuthor(String author);

  List<Book> findByCategory(String category);
}
