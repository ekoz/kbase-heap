/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.common;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.core.io.FileSystemResource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/10 16:04
 */
public class TikaDocumentReaderTests {
  @Test
  public void test() {
    // 指定文档路径
//    String filePath = "D:\\zhanzhao\\Desktop\\幸福嘉苑简介.pdf";
    String filePath = "D:\\zhanzhao\\Desktop\\痛风患者饮食宜忌.docx";

    // 创建FileSystemResource对象，表示文档资源
    FileSystemResource resource = new FileSystemResource(filePath);

    // 创建ParagraphPdfDocumentReader对象，并读取文档
    TikaDocumentReader reader = new TikaDocumentReader(resource);
    List<Document> documents = reader.get();

    // 输出文档内容
    for (Document document : documents) {
      System.out.println(document.getFormattedContent());
    }

  }
}
