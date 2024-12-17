/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.derby;

import org.apache.derby.drda.NetworkServerControl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version 1.0
 * @date 2019/4/10 15:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DerbyUtilsTests {

    @Resource
    JdbcTemplate jdbcTemplate;
    @Resource
    @Qualifier("secondaryJdbcTemplate")
    JdbcTemplate jdbcTemplate2;

    @Test
    public void test() throws SQLException {
//        Connection conn = DerbyUtils.getConnection();
//        DerbyUtils.createTable("");
        System.out.println(jdbcTemplate.getDataSource().getConnection().getCatalog());
    }

    @Test
    public void testCreateTable(){
        String sql = "CREATE TABLE dev_user (" +
                "id bigint NOT NULL generated by default as identity, " +
                "name varchar(64), " +
                "age int " +
                ")";
        jdbcTemplate.update(sql);
    }


    @Test
    public void testInsert(){
        jdbcTemplate.update("insert into dev_user(name, age) values (?, ?)", new Object[]{"李四", 12});
    }

    @Test
    public void testQuery(){
        jdbcTemplate.query("select * from dev_user", resultSet -> {
            System.out.println(resultSet.getString("name"));
        });
    }

    @Test
    public void testQuery2(){
        jdbcTemplate2.query("select * from dev_user", resultSet -> {
            System.out.println(resultSet.getString("name"));
        });
    }

    @Test
    public void testStart() throws Exception {
        System.setProperty("derby.system.home", "D:\\nacos\\data");
        NetworkServerControl serverControl = new NetworkServerControl(InetAddress.getByName("0.0.0.0"),1527, "nacos", "nacos");
        serverControl.start(new PrintWriter(System.out));
        try {
            Thread.currentThread().sleep(60*10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
