/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.es.dao;

import com.eastrobot.heap.es.entity.Notice;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/29 17:51
 */
public interface NoticeRepository extends ElasticsearchCrudRepository<Notice, String> {

    Optional<List<Notice>> findByTitleLike(String title);

    Optional<List<Notice>> findByContentCNLike(String contentCN);

    Optional<List<Notice>> findByContentLike(String content);
}
