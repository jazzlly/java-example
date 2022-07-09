package com.infiniteskills.data;

import com.infiniteskills.data.entities.AccountType;
import com.infiniteskills.data.entities.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class Ch002RowConfigTest {

    @Before
    public void setUp() throws Exception {
        Connection connection= getDbConnJava();
        // ScriptUtils.executeSqlScript(connection, new ClassPathResource("trunc.sql"));
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("populate.sql"));
        connection.close();
    }

    @Test
    public void smoke() {
        /* 创建Hibernate配置， 可以直接通过代码配置，或配置到hibernate.cfg.xml中 */
        Configuration configuration = new Configuration();

        /* 配置包括两个部分*/
        // 1. Annotated class， 所有的Entity类
        configuration.addAnnotatedClass(AccountType.class);

        // 2. Properties, 数据库连接配置
        configuration.setProperties(new Properties() {
            {
                put("hibernate.connection.username", "root");
                put("hibernate.connection.password", "pekall1234");
                put("hibernate.connection.driver_class",
                        "com.mysql.jdbc.Driver");
                put("hibernate.connection.url",
                        "jdbc:mysql://localhost:3306/ifinances");
            }
        });

        /* Building SessionFactory */
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(new StandardServiceRegistryBuilder(
                ).applySettings(configuration.getProperties()).build());

        /* Obtain Session and Call Persistence Methods */
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        AccountType type = new AccountType();

        type.setName("Checking");
        type.setCreatedDate(new Date());
        type.setLastUpdatedDate(new Date());
        type.setCreatedBy("kevinbowersox");
        type.setLastUpdatedBy("kevinbowersox");

        session.save(type);
        session.getTransaction().commit();

        AccountType type1 = (AccountType) session.get(AccountType.class, type.getAccountTypeId());
        assertThat(type1).isEqualTo(type);

        session.close();
    }

    public static Connection getDbConnJava()
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ifinances",
                "root", "pekall1234");
    }
}