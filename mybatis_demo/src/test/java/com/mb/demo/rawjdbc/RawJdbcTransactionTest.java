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
public class RawJdbcTransactionTest {

    Connection connection;

    @Before
    public void setUp() throws Exception {
        System.setProperty("jdbc.drivers", "com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.11.78:3306/raw_jdbc_demo",
                "pekall", "Apple12#$");
    }

    /**
     * 1687791 Query   SET character_set_results = NULL
     * 1687791 Query   SET autocommit=1
     * 1687791 Query   SET sql_mode='NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'
     * 1687791 Query   SET autocommit=0
     * 1687791 Query   insert into orders (name) values ('xiaxia')
     * 1687791 Query   insert into orders (name) values ('kiakia')
     * 1687791 Query   commit
     * @throws SQLException
     */
    @Test
    public void testInsert() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into orders (name) values (?)")) {

            connection.setAutoCommit(false);
            statement.setString(1, "xiaxia");
            statement.executeUpdate();

            statement.setString(1, "kiakia");
            statement.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.commit();
        }
    }

    /**
     * 1687888 Query   SET character_set_results = NULL
     * 1687888 Query   SET autocommit=1
     * 1687888 Query   SET sql_mode='NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'
     * 1687888 Query   SET autocommit=0
     * 1687888 Query   insert into orders (name) values ('xiaxia')
     * 1687888 Query   SAVEPOINT `xiaxia123`
     * 1687888 Query   insert into orders (name) values ('kiakia123')
     * 1687888 Query   ROLLBACK TO SAVEPOINT `xiaxia123`
     * 1687888 Query   commit
     * @throws SQLException
     */

    @Test
    public void testInsertSavePoint() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into orders (name) values (?)")) {

            connection.setAutoCommit(false);
            statement.setString(1, "xiaxia");
            statement.executeUpdate();

            Savepoint savepoint = connection.setSavepoint("xiaxia123");

            statement.setString(1, "kiakia123");
            statement.executeUpdate();

            connection.rollback(savepoint);
            connection.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.commit();
        }
    }
}
