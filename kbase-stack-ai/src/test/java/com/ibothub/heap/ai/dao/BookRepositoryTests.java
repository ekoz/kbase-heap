/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.dao;

import com.ibothub.heap.ai.model.entity.Book;
import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/11 14:14
 */
@SpringBootTest
@Rollback(false)
public class BookRepositoryTests {

  @Resource
  BookRepository bookRepository;

  @Test
  public void testSave() {
    Book book = new Book();
    book.setTitle("Java编程思想");
    book.setAuthor("Bruce Eckel");
    bookRepository.save(book);
  }

  @Test
  public void testSaveAll(){
  // 准备示例数据
    List<Book> sampleBooks = Arrays.asList(
            new Book(null, "Spring实战（第6版）", "编程", "Craig Walls",
                LocalDate.of(2022, 1, 15), "9787115582247"),
          new Book(null, "深入理解Java虚拟机", "编程", "周志明",
                LocalDate.of(2019, 12, 1), "9787111641247"),
          new Book(null, "Java编程思想（第4版）", "编程", "Bruce Eckel",
                LocalDate.of(2007, 6, 1), "9787111213826"),
          new Book(null, "算法（第4版）", "计算机科学", "Robert Sedgewick",
                LocalDate.of(2012, 10, 1), "9787115293800"),
          new Book(null, "云原生架构", "架构设计", "张三",
                LocalDate.of(2023, 3, 15), "9781234567890"),
          new Book(null, "微服务设计模式", "架构设计", "张三",
                LocalDate.of(2021, 8, 20), "9789876543210"),
          new Book(null, "领域驱动设计", "架构设计", "Eric Evans",
                LocalDate.of(2010, 4, 10), "9787111214748"),
          new Book(null, "高性能MySQL", "数据库", "Baron Schwartz",
                LocalDate.of(2013, 5, 25), "9787111464747"),
          new Book(null, "Redis实战", "数据库", "Josiah L. Carlson",
                LocalDate.of(2015, 9, 30), "9787115419378"),
          new Book(null, "深入浅出Docker", "容器技术", "李四",
                LocalDate.of(2022, 11, 20), "9787123456789") );
     // 保存示例数据
    bookRepository.saveAll(sampleBooks);
  }

  @Test
  public void testFindAll() {
    bookRepository.findAll()
      .forEach(System.out::println);
  }

  @Test
  public void testFindByAuthor() {
  bookRepository.findByAuthor("Bruce Eckel")
    .forEach(System.out::println);
  }
}
