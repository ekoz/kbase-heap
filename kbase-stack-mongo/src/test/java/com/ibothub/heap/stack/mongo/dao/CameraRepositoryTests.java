package com.ibothub.heap.stack.mongo.dao;

import com.ibothub.heap.stack.mongo.model.entity.Camera;
import com.ibothub.heap.stack.mongo.model.vo.CameraLocation;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/18 14:17
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CameraRepositoryTests {


  @Resource
  CameraRepository cameraRepository;

  @Test
  public void testFindAll(){
    System.out.println("共有个" + cameraRepository.count() + "相机点位");
    cameraRepository.findAll().forEach(System.out::println);
  }

  @Test
  public void testSave(){
    Camera camera = new Camera();
    camera.setCameraName("三阳路地铁站");

    CameraLocation cameraLocation = CameraLocation.builder()
        .type("Point")
        .coordinates(new double[]{114.307714, 30.604555})
        .build();

    camera.setLocation(cameraLocation);
    cameraRepository.save(camera);
  }
}
