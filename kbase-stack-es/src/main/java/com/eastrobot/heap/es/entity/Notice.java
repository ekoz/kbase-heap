/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.es.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.io.Serializable;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/29 16:42
 */
@Data
@Builder
@Accessors(chain = true)
@Document(indexName="notice", type="_doc")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Mapping(mappingPath = "mapping/es-notice.json")
public class Notice implements Serializable {

    @Id
    private String id;
    @Field
    private String title;
    @Field
    private String content;
    @Field
    private String contentCN;
    @Field
    private String desc;

}
