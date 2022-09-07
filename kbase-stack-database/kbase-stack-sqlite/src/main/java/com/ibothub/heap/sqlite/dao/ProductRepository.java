package com.ibothub.heap.sqlite.dao;

import com.ibothub.heap.sqlite.model.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/9/6 19:04
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
