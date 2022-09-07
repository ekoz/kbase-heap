package com.ibothub.heap.sqlite.model.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 商品
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/9/7 12:47
 */
@Data
@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ExcelProperty
  private Long id;

  /**
   * 产品名称
   */
  @ExcelProperty(index = 3)
  private String name;

  /**
   * 编号
   */
  @ExcelProperty(index = 2)
  private String serialNo;

  /**
   * 品牌
   */
  @ExcelProperty
  private String brand;

  /**
   * 类别，可能为空
   */
  @ExcelProperty(index = 1)
  private String type;

  /**
   * 单位
   */
  @ExcelProperty(index = 5)
  private String unit;

  /**
   * 价格
   */
  @ExcelProperty(index = 6)
  private String price;

  /**
   * 产地
   */
  @ExcelProperty(index = 7)
  private String origin;

}
