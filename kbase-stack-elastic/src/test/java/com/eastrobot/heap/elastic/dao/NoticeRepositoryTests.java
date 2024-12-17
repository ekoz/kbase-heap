/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.elastic.dao;

import com.eastrobot.heap.elastic.entity.Notice;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.MoreLikeThisQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import jakarta.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/29 17:52
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class NoticeRepositoryTests {

    @Resource
    NoticeRepository noticeRepository;

    @Resource
    ElasticsearchOperations elasticsearchOperations;

    @Test
    public void testSave(){
        for (int i=0;i<10;i++){
            Notice notice = Notice.builder()
                    .id(UUID.randomUUID().toString())
                    .title("关于五一放假的通知_" + i)
                    .content("如题")
                    .build();
            noticeRepository.save(notice);
        }
    }

    @Test
    public void testSave2() throws IOException {
        File file = ResourceUtils.getFile("classpath:docs.txt");
        System.out.println(file.getAbsolutePath());
        List<String> list = FileUtils.readLines(file, StandardCharsets.UTF_8);
        List<Notice> noticeList = Lists.newArrayList();
        list.forEach(s -> {
            Notice notice = Notice.builder()
                    .id(UUID.randomUUID().toString())
                    .title(s)
                    .content(s)
                    .contentCN(s)
                    .desc(s)
                    .build();
            noticeList.add(notice);
        });
        noticeRepository.saveAll(noticeList);
    }

    @Test
    public void testSave3(){
        String s = "商品和服务";
        Notice notice = Notice.builder()
                .id(UUID.randomUUID().toString())
                .title(s)
                .content(s)
                .build();
        noticeRepository.save(notice);
    }


    //======================================================================================
    //======================================================================================

    @Test
    public void testFindAll(){
        noticeRepository.findAll().forEach(notice -> System.out.println(notice.toString()));
    }

    @Test
    public void testFindPage(){
        PageRequest pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "contentCN"));
        Page<Notice> page = noticeRepository.findAll(pageable);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        page.forEach(System.out::println);

    }

    @Test
    public void testFindPage2(){
        PageRequest pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "contentCN"));
        SearchPage<Notice> searchPage = noticeRepository.findByTitleOrContent("和服", "服务", pageable);
        System.out.println(searchPage.getTotalElements());
        System.out.println(searchPage.getTotalPages());

        System.out.println(searchPage.getSearchHits().getTotalHits());

        searchPage.forEach(noticeSearchHit -> {
            System.out.println(noticeSearchHit.getContent().getContent());

            System.out.println(noticeSearchHit.getHighlightField("title").get(0));
            System.out.println(noticeSearchHit.getHighlightField("content").get(0));

        });

    }


    @Test
    public void testFindAllAndSetValue(){
        // 找到所有的文档，并且将 content 赋值给 content_cn
        noticeRepository.findAll().forEach(notice -> {
            notice.setContentCN(notice.getContent());
            notice.setDesc(notice.getContent());
            noticeRepository.save(notice);
        });
    }

    @Test
    public void testFindByTitle(){
        noticeRepository.findByTitle("服务")
                .ifPresent(list -> list.forEach(notice -> System.out.println(notice.toString())))
        ;
    }

    @Test
    public void testFindByTitleLike(){
        noticeRepository.findByTitleLike("服")
                .ifPresent(list -> list.forEach(notice -> System.out.println(notice.toString())))
                ;
    }

    @Test
    public void testFindByContentCNLike(){
        noticeRepository.findByContentCNLike("和服")
                .ifPresent(list -> list.forEach(notice -> System.out.println(notice.toString())))
        ;
    }


    @Test
    public void testFindByContentLike(){
        noticeRepository.findByContentLike("和服")
                .ifPresent(list -> list.forEach(notice -> System.out.println(notice.toString())))
        ;
    }

    @Test
    public void testSearchSimilar(){
        PageRequest pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "title"));
        Notice example = Notice.builder().id("39f184ad-b690-4fc5-aa23-163078d4b16b").build();
        Page<Notice> page = noticeRepository.searchSimilar(example, new String[]{"title", "content"}, pageable);

        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        page.forEach(System.out::println);
    }

    @Test
    public void testTermQuery(){

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "美国");

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery query = nativeSearchQueryBuilder.withQuery(matchQueryBuilder).build();

        SearchHits<Notice> searchHits = elasticsearchOperations.search(query, Notice.class);
        searchHits.getSearchHits().forEach(System.out::println);
    }

    @Test
    public void testSearch(){

        MoreLikeThisQueryBuilder moreLikeThisQueryBuilder = QueryBuilders.moreLikeThisQuery(new String[]{"title"}, new String[]{"美国特朗普"}, null);
        moreLikeThisQueryBuilder.minDocFreq(1);
        moreLikeThisQueryBuilder.minTermFreq(1);
        moreLikeThisQueryBuilder.minimumShouldMatch("10%");

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery query = nativeSearchQueryBuilder.withQuery(moreLikeThisQueryBuilder).build();

        SearchHits<Notice> searchHits = elasticsearchOperations.search(query, Notice.class);
        searchHits.getSearchHits().forEach(System.out::println);

    }

}
