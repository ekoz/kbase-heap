/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.es.dao;

import com.eastrobot.heap.es.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/29 17:51
 */
public interface NoticeRepository extends ElasticsearchRepository<Notice, String> {

    Optional<List<Notice>> findByTitleLike(String title);

    Optional<List<Notice>> findByTitle(String title);

    Optional<List<Notice>> findByContentCNLike(String contentCN);

    Optional<List<Notice>> findByContentLike(String content);

    /**
     * 分页查询
     * @param title
     * @param pageable
     * @return
     */
    Optional<Page<Notice>> findByTitleLike(String title, Pageable pageable);

}
