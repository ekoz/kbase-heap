package com.ibothub.heap.stack.mongo.dao;

import com.ibothub.heap.stack.mongo.model.entity.Camera;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/18 14:16
 */
public interface CameraRepository extends MongoRepository<Camera, String> {

}
