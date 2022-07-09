package com.infiniteskills.data;

import com.infiniteskills.data.entities.TimeTest;
import com.infiniteskills.data.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.Connection;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class Ch004TemporalTest {

    @Test
    public void testDateAndTime() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();

            TimeTest test = new TimeTest(new Date());
            session.save(test);
            session.getTransaction().commit();
            session.refresh(test);
            System.out.println(test.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}