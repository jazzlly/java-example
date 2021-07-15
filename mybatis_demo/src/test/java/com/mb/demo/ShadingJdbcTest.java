package com.mb.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ShadingJdbcTest {

    DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        // 配置真实数据源
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

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration(
                "t_order","ds${0..1}.t_order_${0..1}");

        // 配置分库 + 分表策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration(
                        "user_id", "ds${user_id % 2}"));
        orderTableRuleConfig.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration(
                        "order_id", "t_order_${order_id % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 获取数据源对象
        dataSource = ShardingDataSourceFactory.createDataSource(
                dataSourceMap, shardingRuleConfig, new Properties());
    }

    @Test
    public void insertTest() {
        String sql = "insert into t_order values (?, ?)"; // (order_id, user_id)
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            for (int user = 100; user < 110 ; user++) {
                for (int order = 100; order < 110; order++) {
                    preparedStatement.setInt(1, order + user * 10);
                    preparedStatement.setInt(2, user);
                    preparedStatement.execute();
                }
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

    @Test
    public void testYamlConfig() throws URISyntaxException, SQLException, IOException {
        URL url = getClass().getClassLoader().getResource("shading_jdbc.yaml");
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(new File(url.toURI()));

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
}
