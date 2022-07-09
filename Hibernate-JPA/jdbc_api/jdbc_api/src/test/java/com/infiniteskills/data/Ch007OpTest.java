package com.infiniteskills.data;

import com.infiniteskills.data.entities.Bank;
import com.infiniteskills.data.entities.Transaction;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.Connection;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class Ch007OpTest {

    @Before
    public void setUp() throws Exception {
        Connection connection= HibernateUtil.getDbConnJava();
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("populate.sql"));
        connection.close();
    }

    @Test
    public void retrieveEntityNotFound() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            org.hibernate.Transaction transaction = session.beginTransaction();
            Bank bank = (Bank) session.get(Bank.class, 123L);
            assertThat(bank).isNull();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    @Test
    public void retrieveEntity() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            org.hibernate.Transaction transaction = session.beginTransaction();
            Bank bank = (Bank) session.get(Bank.class, 1L);
            assertThat(bank).isNotNull();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    @Test
    public void updateEntity() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Session session1 = HibernateUtil.getSessionFactory().openSession();

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();
            Bank bank = (Bank) session.get(Bank.class, 1L);
            bank.setName("New Hope Bank");
            bank.setLastUpdatedBy("Kevin Bowersox");
            bank.setLastUpdatedDate(new Date());
            transaction.commit();

            org.hibernate.Transaction transaction1 = session1.beginTransaction();
            Bank bank1 = (Bank) session1.get(Bank.class, 1L);
            assertThat(bank1.getName()).isEqualTo(bank.getName());
            assertThat(bank1.getLastUpdatedBy()).isEqualTo(bank.getLastUpdatedBy());
            transaction1.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
            session1.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    @Test
    public void deleteEntity() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();
            Bank bank = (Bank) session.get(Bank.class, 1L);
            assertThat(session.contains(bank)).isTrue();

            session.delete(bank);
            assertThat(session.contains(bank)).isFalse();
            assertThat(session.get(Bank.class, 1L)).isNull();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    @Test
    public void saveAndUpdate() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Transaction transaction = session.beginTransaction();
            Bank detachedBank = (Bank) session.get(Bank.class, 1L);
            transaction.commit();
            session.close();

            Bank transientBank = createBank();
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Transaction transaction2 = session2.beginTransaction();

            assertThat(session2.contains(detachedBank)).isFalse();
            assertThat(session2.contains(transientBank)).isFalse();

            session2.saveOrUpdate(transientBank);
            session2.saveOrUpdate(detachedBank);
            detachedBank.setName("Test Bank 2");

            assertThat(session2.contains(detachedBank)).isTrue();
            assertThat(session2.contains(transientBank)).isTrue();

            transaction2.commit();

            session2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            HibernateUtil.getSessionFactory().close();
        }
    }

    @Test
    public void reattachedObject() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Transaction transaction = session.beginTransaction();
            Bank bank = (Bank) session.get(Bank.class, 1L);
            transaction.commit();
            session.close();
            // "bank" is changed to detached status after session closed

            Session session2 = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Transaction transaction2 = session2.beginTransaction();

            System.out.println(session2.contains(bank));
            session2.update(bank);
            // 'bank" is reattached after update
            bank.setName("Test Bank");
            System.out.println("Method Invoked");
            System.out.println(session2.contains(bank));

            transaction2.commit();
            session2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            HibernateUtil.getSessionFactory().close();
        }
    }

    @Test
    public void flushWillTriggerUpdate() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        try {
            Bank bank = (Bank) session.get(Bank.class, 1L);
            bank.setName("Something Different");
            System.out.println("Calling Flush");
            session.flush(); // trigger update

            bank.setAddressLine1("Another Address Line");
            System.out.println("Calling commit");

            transaction.commit(); // trigger update again
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static Bank createBank() {
        Bank bank = new Bank();
        bank.setName("First United Federal");
        bank.setAddressLine1("103 Washington Plaza");
        bank.setAddressLine2("Suite 332");
        bank.setAddressType("PRIMARY");
        bank.setCity("New York");
        bank.setCreatedBy("Kevin Bowersox");
        bank.setCreatedDate(new Date());
        bank.setInternational(false);
        bank.setLastUpdatedBy("Kevin Bowersox");
        bank.setLastUpdatedDate(new Date());
        bank.setState("NY");
        bank.setZipCode("10000");
        return bank;
    }
}