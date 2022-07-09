package com.infiniteskills.data;

import com.infiniteskills.data.entities.AccountType;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class Ch004TransientTest {

    @Before
    public void setUp() throws Exception {
        Connection connection= HibernateUtil.getDbConnJava();
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("populate.sql"));
        connection.close();
    }

    @Test
    public void smoke() {
        /* Obtain Session and Call Persistence Methods */
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        AccountType type = new AccountType();

        type.setName("Checking");
        type.setCreatedDate(new Date());
        type.setLastUpdatedDate(new Date());
        type.setCreatedBy("kevinbowersox");
        type.setLastUpdatedBy("kevinbowersox");
        type.setTemp("wahaha");

        session.save(type);
        session.getTransaction().commit();

        // 在同一个session之内，@Transient的值保存后，还是可以get到的
        AccountType type1 = (AccountType) session.get(
                AccountType.class, type.getAccountTypeId());
        assertThat(type1.getTemp()).isEqualTo(type.getTemp());

        session.close();

        // 在另一个session中，@Transient的值就看不到了
        session = HibernateUtil.getSessionFactory().openSession();
        AccountType type2 = (AccountType) session.get(AccountType.class, type.getAccountTypeId());
        assertThat(type2.getTemp()).isNull();
        session.close();
    }
}