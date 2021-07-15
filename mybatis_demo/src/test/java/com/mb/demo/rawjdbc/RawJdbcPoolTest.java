package com.mb.demo.rawjdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 从batch执行的sql看，没啥特殊的地方
 */
@Slf4j
public class RawJdbcPoolTest {

    Connection connection;

    @Before
    public void setUp() throws Exception {
        /*
        System.setProperty("jdbc.drivers", "com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.11.78:3306/raw_jdbc_demo",
                "pekall", "Apple12#$");
         */
        Properties properties = new Properties();
        properties.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://192.168.11.78:3306/raw_jdbc_demo");
        properties.setProperty("username", "pekall");
        properties.setProperty("password", "Apple12#$");
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        connection = dataSource.getConnection();
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    /**
     * 1688183 Query   SET character_set_results = NULL
     * 1688183 Query   SET autocommit=1
     * 1688183 Query   SET sql_mode='NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'
     * 1688183 Query   SET autocommit=0
     * 1688183 Query   select @@session.tx_read_only
     * 1688183 Query   insert into orders (name) values ('qiuqiu_0')
     * 1688183 Query   insert into orders (name) values ('qiuqiu_1')
     * 1688183 Query   insert into orders (name) values ('qiuqiu_2')
     * 1688183 Query   insert into orders (name) values ('qiuqiu_3')
     * 1688183 Query   insert into orders (name) values ('qiuqiu_4')
     * 1688183 Query   insert into orders (name) values ('qiuqiu_5')
     * 1688183 Query   insert into orders (name) values ('qiuqiu_6')
     * 1688183 Query   insert into orders (name) values ('qiuqiu_7')
     * 1688183 Query   insert into orders (name) values ('qiuqiu_8')
     * 1688183 Query   insert into orders (name) values ('qiuqiu_9')
     * @throws SQLException
     */
    @Test
    public void testInsertBatch() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into orders (name) values (?)")) {
            connection.setAutoCommit(false);
            for (int i = 0; i < 10; i++) {
                statement.setString(1, "piupiu_" + i);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            log.info("", e);
            connection.rollback();
        } finally {
            connection.commit();
        }
    }
}
