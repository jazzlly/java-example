package com.mb.demo.rawjdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

/**
 * 数量关系：
 * 一个Connection可以创建多个Statement
 * 一个statement可以执行多条不同的sql
 * 一个statement只有一个result set
 *
 * connection, statement, result set都需要被close
 */
@Slf4j
public class RawJdbcTest {

    Connection connection;

    @Before
    public void setUp() throws Exception {
        System.setProperty("jdbc.drivers", "com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.11.78:3306/raw_jdbc_demo",
                "pekall", "Apple12#$");
    }

    /**
     * 创建table, delete table, 修改table
     * @throws SQLException
     */
    @Test
    public void testDDL() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            boolean result = statement.execute("CREATE TABLE foo (name varchar(255))");
            System.out.println(result);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "insert into orders (name) values ('%s')", "xixi");
            System.out.println(sql);

            int result = statement.executeUpdate(sql);
            System.out.println(result);
        }
    }

    @Test
    public void testUpdate() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int updateCnt = statement.executeUpdate(
                    "update orders set name = 'kaka' where name = 'haha'");
            System.out.println(updateCnt);
        }
    }

    @Test
    public void testDelete() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int deleteCnt = statement.executeUpdate(
                    "delete from orders where name = 'xixi'");
            System.out.println(deleteCnt);
        }
    }

    @Test
    public void testSelect() throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from orders")) {
            while (resultSet.next()) {
                log.info("order id: {}", resultSet.getLong("order_id"));
                log.info("order name : {}", resultSet.getString("name"));
            }
        }
    }

    @Test
    public void testPrepareStatement() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from orders where name = ?")) {
            statement.setString(1, "kaka");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                log.info("order id: {}", resultSet.getLong("order_id"));
                log.info("order name : {}", resultSet.getString("name"));
            }
            resultSet.close();

            // 执行不同的sql
            statement.setString(1, "xixi");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                log.info("order id: {}", resultSet.getLong("order_id"));
                log.info("order name : {}", resultSet.getString("name"));
            }
            resultSet.close();
        }
    }

    /**
     * 随机访问结果集
     * @throws SQLException
     */
    @Test
    public void testScroll() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from orders where name = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            statement.setString(1, "kaka");
            ResultSet resultSet = statement.executeQuery();

            log.info("current row: {}", resultSet.getRow());

            resultSet.next(); // 数据库行从1开始
            log.info("order id: {}", resultSet.getLong("order_id"));
            log.info("order name : {}", resultSet.getString("name"));
            log.info("current row: {}", resultSet.getRow());

            resultSet.next();
            log.info("order id: {}", resultSet.getLong("order_id"));
            log.info("order name : {}", resultSet.getString("name"));
            log.info("current row: {}", resultSet.getRow());

            resultSet.previous();
            log.info("order id: {}", resultSet.getLong("order_id"));
            log.info("order name : {}", resultSet.getString("name"));
            log.info("current row: {}", resultSet.getRow());

            resultSet.close();
        }
    }

    /**
     * 通过结果集更新数据库
     *
     * @throws SQLException
     */
    @Test
    public void testUpdateResultSet() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from orders where name = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, "kaka");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                log.info("order id: {}", resultSet.getLong("order_id"));
                log.info("order name : {}", resultSet.getString("name"));

                // 更新结果集
                resultSet.updateString("name", "keke");
                resultSet.updateRow();
            }
            resultSet.close();
        }
    }

    @Test
    public void testMetaData() throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(
                null, null, null, new String[]{"TABLE"});

        ResultSetMetaData metaData1 = tables.getMetaData();
        for (int i = 1; i <= metaData1.getColumnCount(); i++) {
            log.info("column label: {}", metaData1.getColumnLabel(i));
            log.info("column display size: {}", metaData1.getColumnDisplaySize(i));
        }

        while (tables.next()) {
            log.info("table cat: {}", tables.getString("TABLE_CAT"));
            log.info("table schema: {}", tables.getString("TABLE_SCHEM"));
            log.info("table name: {}", tables.getString("TABLE_NAME"));
            log.info("table type: {}", tables.getString("TABLE_TYPE"));
        }
    }

    /**
     * 获取数据集的结构信息
     * @throws SQLException
     */
    @Test
    public void testSelectMeta() throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from orders")) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                log.info("column label: {}", metaData.getColumnLabel(i));
                log.info("column display size: {}", metaData.getColumnDisplaySize(i));
            }
            while (resultSet.next()) {
                log.info("order id: {}", resultSet.getLong("order_id"));
                log.info("order name : {}", resultSet.getString("name"));
            }
        }
    }
}
