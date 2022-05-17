/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.elastic.dao;

import com.eastrobot.heap.elastic.config.Constants;
import com.eastrobot.heap.elastic.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/29 17:51
 */
public interface NoticeRepository extends ElasticsearchRepository<Notice, String> {

    /**
     * 查询关键词，会自动右侧添加*，如，参数为服务，那么搜索的实质关键词是 服务*
     * @param title
     * @return
     */
    Optional<List<Notice>> findByTitleLike(String title);

    /**
     * 分词搜索
     * @param title
     * @return
     */
    Optional<List<Notice>> findByTitle(String title);

    Optional<List<Notice>> findByContentCNLike(String contentCN);

    Optional<List<Notice>> findByContentLike(String content);

    /**
     * 分页查询
     * @param title
     * @param pageable
     * @return
     */
    Optional<Page<Notice>> findByTitle(String title, Pageable pageable);

    /**
     *
     * @param title
     * @param pageable
     * @return
     */
    @Highlight(
            fields = {@HighlightField(name="title"), @HighlightField(name = "content")},
            parameters = @HighlightParameters(preTags = Constants.HL_PRE_TAG, postTags = Constants.HL_POST_TAG)
    )
    SearchPage<Notice> findByTitleOrContent(String title, String content, Pageable pageable);

}
