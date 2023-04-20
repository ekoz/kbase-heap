package com.ibothub.heap.stack.mongo.service;

import com.ibothub.heap.stack.mongo.model.entity.Camera;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/19 15:49
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CameraServiceTests {

  @Resource
  CameraService cameraService;

  @Test
  public void testFindByPoint(){
    // 从 39025 条数据中查询的耗时约 600ms
    cameraService.findByPoint(114.548442, 30.495042, 20 * 1000, 10L).forEach(System.out::println);
    System.out.println("===================================");
    System.out.println("===================================");
    cameraService.findByPoint(114.548442, 30.495042, 20 * 1000).forEach(System.out::println);
  }

  @Test
  public void testFindByCoordinates(){
    // 从 39025 条数据中查询的耗时约 600ms
    List<Camera> list = cameraService
        .findByCoordinates(114.548442, 30.495042, -112.5, 10 * 1000);
    list.forEach(System.out::println);
  }


}
