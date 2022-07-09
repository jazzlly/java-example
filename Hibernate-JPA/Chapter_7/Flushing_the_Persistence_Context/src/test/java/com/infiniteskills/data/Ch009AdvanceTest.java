package com.infiniteskills.data;

import com.infiniteskills.data.entities.Account;
import com.infiniteskills.data.entities.AccountTypeEnum;
import com.infiniteskills.data.entities.Currency;
import com.infiniteskills.data.entities.TimeTest;
import com.infiniteskills.data.entities.ids.CurrencyId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;

public class Ch009AdvanceTest {

    @Before
    public void setUp() throws Exception {
        Connection connection= HibernateUtil.getDbConnJava();
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("populate.sql"));
        connection.close();
    }

    @Test
    public void testCompoundPrimaryKey() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Session session2 = null;
        org.hibernate.Transaction tx = null;
        org.hibernate.Transaction tx2 = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Currency currency = new Currency();
            currency.setCountryName("United States");
            currency.setName("Dollar");
            currency.setSymbol("$");

            session.persist(currency);
            tx.commit();

            session2 = sessionFactory.openSession();
            tx2 = session2.beginTransaction();

            Currency dbCurrency = (Currency) session2.get(Currency.class,
                    new CurrencyId("Dollar", "United States"));
            System.out.println(dbCurrency.getName());

            tx2.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            if (tx2 != null) {
                tx2.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    @Test
    public void testEnum() {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Account account = createNewAccount();
            account.setAccountType(AccountTypeEnum.SAVINGS);

            session.save(account);
            tx.commit();

            Account dbAccount = (Account) session.get(Account.class, account.getAccountId());
            System.out.println(dbAccount.getName());
            System.out.println(dbAccount.getAccountType());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static Account createNewAccount() {
        Account account = new Account();
        account.setCloseDate(new Date());
        account.setOpenDate(new Date());
        account.setCreatedBy("Kevin Bowersox");
        account.setInitialBalance(new BigDecimal("50.00"));
        account.setName("Savings AccountEnum");
        account.setCurrentBalance(new BigDecimal("100.00"));
        account.setLastUpdatedBy("Kevin Bowersox");
        account.setLastUpdatedDate(new Date());
        account.setCreatedDate(new Date());
        return account;
    }
}