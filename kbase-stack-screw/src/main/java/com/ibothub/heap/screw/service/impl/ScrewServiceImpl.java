package com.ibothub.heap.screw.service.impl;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.google.common.collect.Lists;
import com.ibothub.heap.screw.model.vo.req.DriverReq;
import com.ibothub.heap.screw.service.ScrewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/8/3 21:02
 */
@Service
public class ScrewServiceImpl implements ScrewService {


    @Override
    public String create(DriverReq request) {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(request.getDriverClassName())
                .username(request.getUsername())
                .password(request.getPassword())
                .url(request.getMySQLUrl())
                .build();

        String tmpDir = getTmpDir();
        String fileName = System.currentTimeMillis() + "_" + request.getDbName() + "_" + request.getDesc() + "_" + request.getVersion();

        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径，自己mac本地的地址，这里需要自己更换下路径
                .fileOutputDir(tmpDir)
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(EngineFileType.HTML)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker)
                // 指定文件名，无需指定后缀
                .fileName(fileName)
                .build();

        // 生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                .version(request.getVersion())
                .description(request.getDesc())
                .dataSource(dataSource)
                .engineConfig(engineConfig)
                .produceConfig(getProcessConfig(request))
                .build();

        // 执行生成
        new DocumentationExecute(config).execute();

        return fileName + EngineFileType.HTML.getFileSuffix();

    }

    @Override
    public File getFile(String fileName) {
        String separator = "/";
        String tmpDir = getTmpDir();
        tmpDir = tmpDir.replaceAll("\\\\", separator);
        if (!tmpDir.endsWith(separator)) {
            tmpDir += separator;
        }
        return new File(tmpDir + fileName);
    }


    /**
     * 配置想要生成的表+ 配置想要忽略的表
     * @return 生成表配置
     */
    private ProcessConfig getProcessConfig(DriverReq request){
        String paramSpliter = ",";

        // 忽略表名 "aaa","ddd"
        List<String> ignoreTableNameList = Lists.newArrayList();
        if (StringUtils.isNotBlank(request.getIgnoreTableName())) {
            ignoreTableNameList = Lists.newArrayList(request.getIgnoreTableName().split(paramSpliter));
        }

        // 忽略表前缀，如忽略a开头的数据库表 "a","t"
        List<String> ignorePrefixList = Lists.newArrayList();
        if (StringUtils.isNotBlank(request.getIgnorePrefix())) {
            ignorePrefixList = Lists.newArrayList(request.getIgnorePrefix().split(paramSpliter));
        }

        // 忽略表后缀 "_test","czb_"
        List<String> ignoreSuffixList = Lists.newArrayList();
        if (StringUtils.isNotBlank(request.getIgnoreSuffix())) {
            ignoreSuffixList = Lists.newArrayList(request.getIgnoreSuffix().split(paramSpliter));
        }

        return ProcessConfig.builder()
                //根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                //忽略表名
                .ignoreTableName(ignoreTableNameList)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefixList)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffixList).build();
    }

    private String getTmpDir(){
        String tmpDir = System.getProperty("java.io.tmpdir");
        return tmpDir + "/screw";
    }
}
