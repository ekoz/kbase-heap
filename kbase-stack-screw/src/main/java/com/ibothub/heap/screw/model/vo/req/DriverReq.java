package com.ibothub.heap.screw.model.vo.req;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/8/3 20:56
 */
@Data
public class DriverReq {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "用户密码不能为空")
    private String password;

    @Deprecated
    private String url;

    @NotBlank(message = "数据库Ip不能为空")
    private String hostIp;

    @NotBlank(message = "数据库名称不能为空")
    private String dbName;

    private Integer port = 3306;

    private String driverClassName = "com.mysql.cj.jdbc.Driver";

    private String version = "1.0.0";

    private String desc = "生成文档信息描述";

    private String ignoreTableName;

    private String ignorePrefix;

    private String ignoreSuffix;

    /**
     * 获取 MySQL 连接地址
     * @return
     */
    public String getMySQLUrl(){
        if (StringUtils.isBlank(this.url)) {
            return String.format("jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=utf-8" +
                            "&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&useInformationSchema=true",
                    this.hostIp, this.port, this.dbName);
        }
        return this.url;
    }

}
