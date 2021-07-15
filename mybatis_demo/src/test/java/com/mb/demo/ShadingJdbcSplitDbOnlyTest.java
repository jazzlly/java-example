package com.mb.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * 仅仅分库的demo
 */
public class ShadingJdbcSplitDbOnlyTest {

    DataSource dataSource;
    /*
 CREATE TABLE `ds0`.`t_order` (
  `user_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ds1`.`t_order` (
  `user_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ds0`.`t_address` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ds1.`t_address` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */

    @Before
    public void setUp() throws Exception {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        // shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBroadcastTables().add("t_address");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration(
                        "user_id", "ds${user_id % 2}"));
        dataSource =  ShardingDataSourceFactory.createDataSource(createDatasourceMap(),
                shardingRuleConfig, getGlobalProperties());
    }

    private Properties getGlobalProperties() {
        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");
        return properties;
    }
    private static TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_order");
        result.setKeyGeneratorConfig(new KeyGeneratorConfiguration(
                "SNOWFLAKE", "order_id", getProperties()));
        return result;
    }

//    private static TableRuleConfiguration getOrderItemTableRuleConfiguration() {
//        TableRuleConfiguration result = new TableRuleConfiguration("t_order_item");
//        result.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "order_item_id", getProperties()));
//        return result;
//    }

    private static Properties getProperties() {
        Properties result = new Properties();
        result.setProperty("worker.id", "123");
        return result;
    }

    private static Map<String, DataSource> createDatasourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://192.168.11.78:3306/ds0");
        dataSource1.setUsername("pekall");
        dataSource1.setPassword("Apple12#$");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第二个数据源
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://192.168.11.78:3306/ds1");
        dataSource2.setUsername("pekall");
        dataSource2.setPassword("Apple12#$");
        dataSourceMap.put("ds1", dataSource2);
        return dataSourceMap;
    }

    @Test
    public void insertTest() {
        String sql = "insert into t_order (user_id) values (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            for (int userId = 0; userId < 10; userId++) {
                for (int order = 0; order < 10; order++) {
                    preparedStatement.setInt(1, userId);
                    preparedStatement.execute();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void insertBroadCastTable() {
        String sql = "insert into t_address (id, name) values (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            for (int i = 0; i < 10; i++) {
                preparedStatement.setLong(1, System.currentTimeMillis());
                preparedStatement.setString(2, UUID.randomUUID().toString());
                preparedStatement.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Test
    public void smoke1() throws URISyntaxException, SQLException, IOException {
        String sql = "SELECT o.* FROM t_order o WHERE o.user_id=? AND o.order_id=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, 11);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while(rs.next()) {
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getInt(2));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void smoke2() {
        String sql = "SELECT o.* FROM t_order o WHERE o.order_id > 100";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            // preparedStatement.setInt(1, 2);
            // preparedStatement.setInt(2, 11);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while(rs.next()) {
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getInt(2));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
