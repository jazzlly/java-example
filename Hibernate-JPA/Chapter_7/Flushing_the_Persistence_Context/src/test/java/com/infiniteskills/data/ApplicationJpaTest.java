package com.infiniteskills.data;

import com.infiniteskills.data.entities.Bank;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.persistence.*;
import java.sql.Connection;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationJpaTest {

    @Before
    public void setUp() throws Exception {
        Connection connection= HibernateUtil.getDbConnJava();
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("populate.sql"));
        connection.close();
    }

    @Test
    public void smoke() {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try{
            factory = Persistence.createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Bank bank = createBank();
            em.persist(bank);

            Bank bank1 = em.getReference(Bank.class, bank.getBankId());
            assertThat(bank1).isNotNull();
            assertThat(bank1).isEqualTo(bank);

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
            factory.close();
        }

    }

    @Test
    public void retrieveObject() {
        EntityManager em = HibernateUtil.getJpaEntityManager();
        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        Bank bank = em.getReference(Bank.class, 1L);
        assertThat(em.contains(bank)).isTrue();

        tx.commit();
        em.close();
    }

    @Test
    public void updateEntity() {
        EntityManager em = HibernateUtil.getJpaEntityManager();
        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        Bank bank = em.getReference(Bank.class, 1L);
        assertThat(em.contains(bank)).isTrue();
        bank.setName("foo bank");
        tx.commit();

        Bank bank1 = em.getReference(Bank.class, 1L);
        assertThat(bank1.getName()).isEqualTo(bank.getName());
        // assertThat(bank).isEqualTo(bank1);
        em.close();
    }

    @Test
    public void mergeEntity() {
        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = HibernateUtil.getJpaEntityManager();
            tx =  em.getTransaction();
            tx.begin();

            Bank bank = em.find(Bank.class, 1L);
            em.detach(bank);
            System.out.println(em.contains(bank));

            bank.setName("Something else");
            Bank bank2 = em.merge(bank);

            bank.setName("Something else 2");
            tx.commit();

            Bank bank3 = em.getReference(Bank.class, 1L);
            assertThat(bank3.getName()).isEqualTo(bank2.getName());
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
    }

    // @Test(expected = javax.persistence.EntityNotFoundException.class)
    @Test
    public void removeEntity() {
        EntityManager em = HibernateUtil.getJpaEntityManager();
        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        Bank bank = em.find(Bank.class, 1L);
        em.remove(bank);
        tx.commit();
        em.close();
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