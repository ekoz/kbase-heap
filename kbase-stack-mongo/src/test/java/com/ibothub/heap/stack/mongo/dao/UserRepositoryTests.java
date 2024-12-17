package com.ibothub.heap.stack.mongo.dao;

import com.google.common.collect.Lists;
import com.ibothub.heap.stack.mongo.model.entity.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/4/27 10:47
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserRepositoryTests {

    @Resource
    UserRepository userRepository;

    @Resource
    MongoTemplate mongoTemplate;

    @Test
    public void testSave(){

        User user = User.builder()
                .username("宋江")
                .birthday(LocalDate.of(1573, 2, 10))
                .empDate(LocalDate.of(1588, 10, 2))
                .password("888888")
                .role("领导")
                .habits(new String[]{"舞枪", "弄棒", "写诗"})
                .build();

        userRepository.save(user);

    }

    @Test
    public void testBatchSave(){
        List<User> list = Lists.newArrayList();
        list.add(User.builder()
                .username("宋江")
                .birthday(LocalDate.of(1573, 2, 10))
                .empDate(LocalDate.of(1588, 10, 2))
                .password("888888")
                .role("领导")
                .habits(new String[]{"舞枪", "弄棒", "写诗"})
                .build());
        list.add(User.builder()
                .username("卢俊义")
                .birthday(LocalDate.of(1575, 5, 10))
                .empDate(LocalDate.of(1595, 1, 2))
                .password("888888")
                .role("领导")
                .habits(new String[]{"舞枪", "弄棒", "员外"})
                .build());
        list.add(User.builder()
                .username("吴用")
                .birthday(LocalDate.of(1573, 2, 10))
                .empDate(LocalDate.of(1588, 10, 2))
                .password("888888")
                .role("领导")
                .habits(new String[]{"孙子兵法", "写书", "写诗"})
                .build());
        list.add(User.builder()
                .username("公孙胜")
                .birthday(LocalDate.of(1573, 2, 10))
                .empDate(LocalDate.of(1588, 10, 2))
                .password("888888")
                .role("领导")
                .habits(new String[]{"算命", "作法", "摆阵"})
                .build());

        userRepository.saveAll(list);
    }


    @Test
    public void testQuery(){

        userRepository.findByUsernameLike("宋")
                .forEach(System.out::println);

        System.out.println("=================================");

        userRepository.findByRole("领导")
                .forEach(System.out::println);

        System.out.println("=================================");

        userRepository.findByHabitsContains("孙子兵法")
                .forEach(System.out::println);

        System.out.println("=================================");

        userRepository.findByHabitsContains(new String[]{"舞枪", "写诗"})
                .forEach(System.out::println);

        System.out.println("=================================");


    }

    @Test
    public void testFindById(){

        String id = "6088d21cce1ea15e34b7c1fe";

        // 1、 findById
        System.out.println(userRepository.findById(id).isPresent());

        // 2、findOne
        User user = User.builder()
                .id(id)
                .build();

        Example<User> example = Example.of(user);

        System.out.println(userRepository.findOne(example).isPresent());


        // 3、rewrite function
        ObjectId objectId = new ObjectId(id);

        DBObject dbObject = BasicDBObjectBuilder
                .start("_id", objectId)
                .get();

        System.out.println(dbObject.toString());

        System.out.println(userRepository.findByObjectId(objectId).isPresent());

        // 4、mongoTemplate
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);

        System.out.println(mongoTemplate.findOne(query, User.class).getUsername());

    }

    @Test
    public void testUpdate(){
        String id = "6088d21cce1ea15e34b7c1fe";
        userRepository
                .findById(id)
                .ifPresent(user -> {
                    System.out.println(user.getUsername());
                    user.setUsername("宋江111");
                    userRepository.save(user);
                });

    }


    @Test
    public void testDelete(){
        userRepository.deleteAll();
    }

}
