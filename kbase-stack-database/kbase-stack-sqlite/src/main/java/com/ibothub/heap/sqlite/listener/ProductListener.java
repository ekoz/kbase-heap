package com.ibothub.heap.sqlite.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.ibothub.heap.sqlite.dao.ProductRepository;
import com.ibothub.heap.sqlite.model.entity.Product;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/9/7 12:56
 */
@Slf4j
public class ProductListener implements ReadListener<Product> {

  /**
   * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
   */
  private static final int BATCH_COUNT = 100;
  /**
   * 缓存的数据
   */
  private List<Product> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
  /**
   * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
   */
  private ProductRepository productRepository;

  /**
   * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
   *
   * @param productRepository
   */
  public ProductListener(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * 这个每一条数据解析都会来调用
   *
   * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
   * @param context
   */
  @Override
  public void invoke(Product data, AnalysisContext context) {

    String sheetName = context.readSheetHolder().getSheetName();

    log.info("解析到一条数据:{}", JSON.toJSONString(data));
    data.setId(null);
    data.setBrand(sheetName);
    cachedDataList.add(data);
    // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
    if (cachedDataList.size() >= BATCH_COUNT) {
      saveData();
      // 存储完成清理 list
      cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    }
  }

  /**
   * 所有数据解析完成了 都会来调用
   *
   * @param context
   */
  @Override
  public void doAfterAllAnalysed(AnalysisContext context) {
    // 这里也要保存数据，确保最后遗留的数据也存储到数据库
    saveData();
    log.info("所有数据解析完成！");
  }

  /**
   * 加上存储数据库
   */
  private void saveData() {
    log.info("{}条数据，开始存储数据库！", cachedDataList.size());
    productRepository.saveAll(cachedDataList);
    log.info("存储数据库成功！");
  }
}
