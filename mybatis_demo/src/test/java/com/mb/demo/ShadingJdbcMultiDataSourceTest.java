package com.mb.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
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
import java.util.*;

/**
 * 多数据源的demo, 有bug!
 */
public class ShadingJdbcMultiDataSourceTest {

    DataSource dataSource;
    /*
 CREATE TABLE `ds0`.`t_user` (
  `id` bigint NOT NULL,
  `account` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ds1`.`t_org` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */

    @Before
    public void setUp() throws Exception {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrgTableRuleConfiguration());
        // shardingRuleConfig.getBroadcastTables().add("t_address");
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
//                new InlineShardingStrategyConfiguration(
//                        "user_id", "ds${user_id % 2}"));
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
//                new InlineShardingStrategyConfiguration(
//                        "user_id", "ds${user_id % 2}"));
        dataSource =  ShardingDataSourceFactory.createDataSource(createDatasourceMap(),
                shardingRuleConfig, getGlobalProperties());
    }

    private Properties getGlobalProperties() {
        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");
        return properties;
    }

    private static TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_user");
        result.setDatabaseShardingStrategyConfig(new HintShardingStrategyConfiguration(
                new HintShardingAlgorithm() {
                    @Override
                    public Collection<String> doSharding(Collection availableTargetNames, HintShardingValue shardingValue) {
                        System.out.println(availableTargetNames);
                        System.out.println(shardingValue);
                        return Arrays.asList("ds0");
                    }
                }
        ));
        result.setKeyGeneratorConfig(new KeyGeneratorConfiguration(
                "SNOWFLAKE", "id", getProperties()));
        return result;
    }

    private static TableRuleConfiguration getOrgTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_org");
        result.setDatabaseShardingStrategyConfig(new HintShardingStrategyConfiguration(
                (availableTargetNames, shardingValue) -> Arrays.asList("ds1")
        ));
        result.setKeyGeneratorConfig(new KeyGeneratorConfiguration(
                "SNOWFLAKE", "id", getProperties()));

        System.out.println(result.getActualDataNodes());
        return result;
    }

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
    public void insertUserTest() {
        String sql = "insert into t_user (account) values (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            for (int userId = 0; userId < 1; userId++) {
                preparedStatement.setString(1, "account" + userId);
                preparedStatement.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void insertOrgTest() {
        String sql = "insert into t_org (name) values (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            for (int userId = 0; userId < 10; userId++) {
                preparedStatement.setString(1, "name" + userId);
                preparedStatement.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
