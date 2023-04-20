package com.ibothub.heap.stack.mongo.service;

import com.ibothub.heap.stack.mongo.model.entity.Camera;
import com.ibothub.heap.stack.mongo.util.PolygonUtil;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/18 17:40
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class BaiduMapServiceTests {

  @Resource
  BaiduMapService baiduMapService;

  @Resource
  MongoTemplate mongoTemplate;

  @Test
  public void testFind(){
    GlobalCoordinates start = new GlobalCoordinates(30.495042, 114.548442);
    Polygon sectorArea = PolygonUtil.getSectorArea(start, -67.5, -45.0, 10 * 1000);

    Criteria criteria = Criteria.where("location").within(sectorArea);
    Query query = Query.query(criteria);
    List<Camera> list = mongoTemplate.find(query, Camera.class);

    System.out.println(list.size());
    list.forEach(System.out::println);
  }


  @Test
  public void testLoadCameras() throws IOException {
    baiduMapService.insert();
  }

}
