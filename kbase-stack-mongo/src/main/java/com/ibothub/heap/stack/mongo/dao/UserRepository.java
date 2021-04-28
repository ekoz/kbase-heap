package com.ibothub.heap.stack.mongo.dao;

import com.ibothub.heap.stack.mongo.model.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/4/27 10:35
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {


    List<User> findByUsernameLike(String username);

    List<User> findByRole(String role);

    List<User> findByHabitsContains(String habit);

    /**
     * 这里的包含是或的关系
     * @param habits
     * @return
     */
    @Query(fields = "{'id': 1, 'username': 1}")
    List<User> findByHabitsContains(String[] habits);

    @Query("{'_id': ?0}")
    Optional<User> findByObjectId(ObjectId id);
}
