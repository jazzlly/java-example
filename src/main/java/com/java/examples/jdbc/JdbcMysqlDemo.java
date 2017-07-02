package com.java.examples.jdbc;

import java.sql.*;

/**
 * Created by jiangrui on 6/12/15.
 */
public class JdbcMysqlDemo {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("load db driver success!");

        String url = "jdbc:mysql://localhost:3306/mdm_reactor" +
                "?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
        String username = "root";
        String password = "pekall1234";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement stat = conn.createStatement();
            stat.executeUpdate(DROP_TABLE);
            stat.executeUpdate(CREATE_TABLE);
            stat.executeUpdate(INSERT_ROW);

//            final String query = "SELECT * from mdm_auth_test where method = ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setString(1, "GET");

            Statement query = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = query.executeQuery("SELECT * from mdm_auth_test");

            System.out.println("SELECT * from mdm_auth_test");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString("perm_group"));
            }

            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            for (int i = 1; i < count; i++) {
                if (i > 1) System.out.print(",");
                System.out.print(metaData.getColumnLabel(i));
            }

            stat.executeUpdate(DROP_TABLE);
            stat.close();
            query.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS `mdm_auth_test`;";
    private static final String CREATE_TABLE = "CREATE TABLE `mdm_auth_test` (" +
            "            `id` varchar(32) NOT NULL," +
            "    `description` varchar(255) DEFAULT NULL," +
            "    `perm_group` varchar(255) DEFAULT NULL," +
            "    `method` varchar(255) DEFAULT NULL," +
            "    `name` varchar(255) NOT NULL," +
            "    `resource` varchar(255) NOT NULL," +
            "    `domain` varchar(64) DEFAULT NULL," +
            "    `source_id` varchar(32) DEFAULT NULL," +
            "    PRIMARY KEY (`id`)" +
            "    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private static final String INSERT_ROW =
            "INSERT INTO `mdm_auth_test` VALUES ('perm_id_aaaaaaaaaaaaaaaaaaaaaa01'," +
            "'查看管理员信息','管理员','GET','查看管理员','/mdm/v1/enterprise_admin/**',NULL,NULL);";

}
