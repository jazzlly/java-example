package com.infiniteskills.data;

import com.infiniteskills.data.entities.Bank;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.criteria.OrderImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class Ch011Criteria extends TestBase {

    @Before
    public void setUp() throws Exception {
        Connection connection= HibernateUtil.getDbConnJava();
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("populate.sql"));
        connection.close();
    }

    @Test
    public void simpleHibernateCriteria() {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Bank.class);
        criteria.addOrder(Order.desc("name"));
        List<Bank> banks = criteria.list();

        for (Bank t : banks) {
            System.out.println(t.getName());
        }
        session.close();
    }

    @Test
    public void hibernateRestrictions() {
        Session session = sessionFactory.openSession();
        Criterion criterion1 = Restrictions.like("name", "%rd%");
        Criterion criterion2 = Restrictions.eq("international", false);
        List<Bank> banks = session
                .createCriteria(Bank.class).add(Restrictions.and(criterion1, criterion2))
                .addOrder(Order.desc("name")).list();
        for (Bank t : banks) {
            System.out.println(t.getName());
        }
    }

    @Test
    public void hibernatePaging() {
        Session session = sessionFactory.openSession();
        Criterion criterion1 = Restrictions.like("name", "%d%");
        Criterion criterion2 = Restrictions.eq("international", false);
        List<Bank> banks = session
                .createCriteria(Bank.class)
                .add(Restrictions.and(criterion1, criterion2))
                .addOrder(Order.asc("name"))
                .setFirstResult(1)
                .setMaxResults(3)
                .list();
        for (Bank t : banks) {
            System.out.println(t.getName());
        }
    }

    @Test
    public void simpleJpaCriteria() {
        EntityManager em = entityManagerFactory.createEntityManager();

        //select t from transaction t
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bank> criteriaQuery = cb.createQuery(Bank.class);

        Root<Bank> root = criteriaQuery.from(Bank.class);
        criteriaQuery.select(root);

        TypedQuery<Bank> query = em.createQuery(criteriaQuery);
        List<Bank> transactions = query.getResultList();

        for (Bank t : transactions) {
            System.out.println(t.getName());
        }
    }

    @Test
    public void jpaCriteria() {
        EntityManager em = entityManagerFactory.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bank> criteriaQuery = cb.createQuery(Bank.class);
        Root<Bank> root = criteriaQuery.from(Bank.class);
        criteriaQuery.select(root).where(cb.and(
                cb.like(root.get("name"), "%rd%"),
                cb.equal(root.get("international"), false)

        ));

        TypedQuery<Bank> query = em.createQuery(criteriaQuery);
        List<Bank> transactions = query.getResultList();

        for (Bank t : transactions) {
            System.out.println(t.getName());
        }
    }

    @Test
    public void jpaPaging() {
        EntityManager em = entityManagerFactory.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bank> criteriaQuery = cb.createQuery(Bank.class);
        Root<Bank> root = criteriaQuery.from(Bank.class);
        criteriaQuery.select(root).where(cb.and(
                cb.like(root.get("name"), "%r%"),
                cb.equal(root.get("international"), false)
        )).orderBy(cb.asc(root.get("name")));

        TypedQuery<Bank> query = em.createQuery(criteriaQuery);
        query.setFirstResult(1);
        query.setMaxResults(5);
        List<Bank> transactions = query.getResultList();

        for (Bank t : transactions) {
            System.out.println(t.getName());
        }
    }

}