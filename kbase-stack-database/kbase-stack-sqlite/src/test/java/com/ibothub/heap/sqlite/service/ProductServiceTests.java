package com.ibothub.heap.sqlite.service;

import com.alibaba.excel.EasyExcel;
import com.ibothub.heap.sqlite.dao.ProductRepository;
import com.ibothub.heap.sqlite.listener.ProductListener;
import com.ibothub.heap.sqlite.model.entity.Product;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/9/7 12:51
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductServiceTests {

  @Resource
  ProductRepository productRepository;

  @Test
  public void testLoadByExcel(){

    productRepository.deleteAll();

    String fileName = "D:\\Ekoz\\Items\\2022-09-04 三姨\\202209041010.xlsx";

    EasyExcel.read(fileName, Product.class, new ProductListener(productRepository)).headRowNumber(3).doReadAll();
  }

  @Test
  public void testFindAll(){
    productRepository.findAll()
        .forEach(System.out::println);
  }


}
