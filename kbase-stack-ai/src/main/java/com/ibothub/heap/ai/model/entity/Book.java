/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/10 17:04
 */
@Entity
@Table(name = "sys_book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  /**
   * 书名
   */
  private String title;

  /**
   * 类别
   */
  private String category;

  /**
   * 作者
   */
  private String author;

  /**
   * 出版日期
   */
  private LocalDate publicationDate;

  /**
   * ISBN编码
   */
  private String isbn;
}
