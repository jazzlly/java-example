package com.infiniteskills.data;

import com.infiniteskills.data.entities.Bank;
import com.infiniteskills.data.model.QSecEventAlertEntity;
import com.infiniteskills.data.model.SecEventAlertEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.util.List;

public class Ch100SecRuleServer extends TestBase {

    /*
    @Before
    public void setUp() throws Exception {
        Connection connection= HibernateUtil.getDbConnJava();
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("sec_rule_init.sql"));
        connection.close();
    }
    */

    @Test
    public void simpleHibernateCriteria() {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SecEventAlertEntity.class);
        criteria.addOrder(Order.desc("alertLevel"));
        List<SecEventAlertEntity> banks = criteria.list();

        for (SecEventAlertEntity t : banks) {
            System.out.println(t);
        }
        session.close();
    }

    @Test
    public void hibernateRestrictions() {
        Session session = sessionFactory.openSession();
        Criterion criterion1 = Restrictions.eq(
                "alertReason", SecEventAlertEntity.ALERT_REASON_SIM_VIOLATION);
        Criterion criterion2 = Restrictions.eq(
                "alertStat", SecEventAlertEntity.ALERT_STAT_ONGOING);
        List<SecEventAlertEntity> banks = session
                .createCriteria(SecEventAlertEntity.class)
                .add(Restrictions.and(criterion1, criterion2))
                .addOrder(Order.desc("startTime")).list();
        for (SecEventAlertEntity t : banks) {
            System.out.println(t);
        }
    }

    @Test
    public void hibernatePaging() {
        Session session = sessionFactory.openSession();
        Criterion criterion1 = Restrictions.eq(
                "alertReason", SecEventAlertEntity.ALERT_REASON_SIM_VIOLATION);
        Criterion criterion2 = Restrictions.eq(
                "alertStat", SecEventAlertEntity.ALERT_STAT_ONGOING);
        List<SecEventAlertEntity> banks = session
                .createCriteria(SecEventAlertEntity.class)
                .add(Restrictions.and(criterion1, criterion2))
                .addOrder(Order.desc("targetName"))
                .setFirstResult(0)
                .setMaxResults(3)
                .list();
        for (SecEventAlertEntity t : banks) {
            System.out.println(t);
        }
    }

    @Test
    public void simpleJpaCriteria() {
        QSecEventAlertEntity alert = QSecEventAlertEntity.secEventAlertEntity;

        JPAQuery query = new JPAQuery(HibernateUtil.getJpaEntityManager());
        JPAQueryFactory queryFactory = new JPAQueryFactory(HibernateUtil.getJpaEntityManager());
        List<SecEventAlertEntity> alerts = queryFactory.selectFrom(alert)
                .where(alert.alertReason.eq(SecEventAlertEntity.ALERT_REASON_ROOT_VIOLATION),
                        alert.alertStat.eq(SecEventAlertEntity.ALERT_STAT_ONGOING))
                .orderBy(alert.startTime.desc())
                .limit(5)
                .fetch();
        alerts.forEach(entity -> {
            System.out.println(entity);
        });
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