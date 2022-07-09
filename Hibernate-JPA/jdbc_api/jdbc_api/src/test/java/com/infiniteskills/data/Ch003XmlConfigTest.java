package com.infiniteskills.data;

import com.infiniteskills.data.entities.AccountType;
import com.infiniteskills.data.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

public class Ch003XmlConfigTest {

    @Before
    public void setUp() throws Exception {
        Connection connection= HibernateUtil.getDbConnJava();
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("populate.sql"));
        connection.close();
    }

    @Test
    public void save() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        User user = new User();
        user.setBirthDate(new Date());
        user.setCreatedBy("kevin");
        user.setCreatedDate(new Date());
        user.setEmailAddress("kmb385@yahoo.com");
        user.setFirstName("Kevin");
        user.setLastName("Bowersox");
        user.setLastUpdatedBy("kevin");
        user.setLastUpdatedDate(new Date());

        session.save(user);
        transaction.commit();

        User user1 = (User) session.get(User.class, user.getUserId());
        assertThat(user).isEqualTo(user1);

        session.close();
    }

    @Test
    public void update() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();

            User user = new User();
            user.setBirthDate(null);
            user.setCreatedBy("kevin");
            user.setCreatedDate(new Date());
            user.setEmailAddress("kmb385@yahoo.com");
            user.setFirstName("Kevin");
            user.setLastName("Bowersox");
            user.setLastUpdatedBy("kevin");
            user.setLastUpdatedDate(new Date());
            user.setBirthDate(new Date());

            session.save(user);
            session.getTransaction().commit();

            session.beginTransaction();
            User dbUser = (User) session.get(User.class, user.getUserId());
            dbUser.setFirstName("Joe");
            session.update(dbUser);
            session.getTransaction().commit();

            User user1 = (User) session.get(User.class, user.getUserId());
            assertThat(user1.getFirstName()).isEqualTo(dbUser.getFirstName());

        } catch (Exception e) {
            e.printStackTrace();
            session.close();
        }

    }
}