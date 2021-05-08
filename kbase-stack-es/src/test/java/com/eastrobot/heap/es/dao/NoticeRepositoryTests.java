/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.es.dao;

import com.eastrobot.heap.es.entity.Notice;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
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
        list.forEach(s -> {
            Notice notice = Notice.builder()
                    .id(UUID.randomUUID().toString())
                    .title(s)
                    .content(s)
                    .contentCN(s)
                    .desc(s)
                    .build();
            noticeRepository.save(notice);
        });
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

}
