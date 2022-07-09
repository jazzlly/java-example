package com.infiniteskills.data;

import com.infiniteskills.data.model.*;
import com.infiniteskills.data.vo.FooBean;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.querydsl.core.types.Projections.list;
import static org.assertj.core.api.Assertions.assertThat;

import static com.querydsl.core.group.GroupBy.*;


public class Ch200QueryDsl extends TestBase {
    /*
    @Before
    public void setUp() throws Exception {
        Connection connection= HibernateUtil.getDbConnJava();
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("sec_rule_init.sql"));
        connection.close();
    }
    */

    @Test
    public void smoke() {
        // basic query
        JPAQuery jpaQuery = new JPAQuery(HibernateUtil.getJpaEntityManager());
        HibernateQuery hibernateQuery = new HibernateQuery(
                HibernateUtil.getSessionFactory().openSession());

        // query factory
        JPAQueryFactory jpaQueryFactory =
                new JPAQueryFactory(HibernateUtil.getJpaEntityManager());
        HibernateQueryFactory hibernateQueryFactory =
                new HibernateQueryFactory(HibernateUtil.getSessionFactory().openSession());
    }

    @Test
    public void query() {
        SecEventAlertEntity entity = qFac.selectFrom(qAlert).fetchFirst();
        assertThat(entity).isNotNull();
        System.out.println(entity);
    }

    @Test
    public void basicQuery() {
       List<SecEventAlertEntity> alerts = qFac.selectFrom(qAlert)
               .where(qAlert.alertReason.eq(SecEventAlertEntity.ALERT_REASON_ROOT_VIOLATION),
                       qAlert.alertStat.eq(SecEventAlertEntity.ALERT_STAT_DISMISSED))
               .orderBy(qAlert.targetName.desc())
               .limit(5)
               .fetch();
       alerts.forEach(entity -> {
           System.out.println(entity);
       });
    }

    @Test
    public void tuple() {
        List<Tuple> alerts = qFac
                .select(qAlert.targetName, qAlert.alertReason, qAlert.startTime).from(qAlert)
                .limit(5)
                .fetch();
        alerts.forEach(tuple -> {
            System.out.println(tuple);
        });
    }

    @Test
    public void groupBy1() {
        List<Tuple> alerts = qFac
                .select(qAlert.alertReason, qAlert.countDistinct()).from(qAlert)
                .groupBy(qAlert.alertReason)
                .fetch();
        alerts.forEach(tuple -> {
            System.out.println(tuple);
        });
    }

    @Test
    public void crossJoin() {
        List<Tuple> tuples = qFac.select(qAlert.targetName, qAlertRel.operationCode, qAlertRel.operationStat)
                .from(qAlert, qAlertRel)
                .where(qAlert.id.eq(qAlertRel.alertId),
                        qAlertRel.operationCode.eq(OperationEntity.OP_CODE_DISABLE_UAS_LOGIN),
                        qAlertRel.operationStat.eq(OperationEntity.OP_STAT_CANCEL)
                ).fetch();

        tuples.forEach(tuple -> {
            System.out.println(tuple);
        });
    }

    @Test
    public void innerJoin() {
        // todo: join
        List<Tuple> tuples = qFac.select(qAlert.targetName, qAlertRel.operationCode)
                .from(qAlert).join(qAlertRel)
                .on(qAlert.id.eq(qAlertRel.alertId))
                //.where(qAlertRel.operationCode.eq(OperationEntity.OP_CODE_DISABLE_UAS_LOGIN),
                //        qAlertRel.operationStat.eq(OperationEntity.OP_STAT_CANCEL))
                .fetch();

        tuples.forEach(tuple -> {
            System.out.println(tuple);
        });
    }

    @Test
    public void subQuery() {
        List<SecEventAlertEntity> alerts = qFac.selectFrom(qAlert)
                .where(qAlert.id.in(
                        JPAExpressions.select(qAlertRel.alertId).from(qAlertRel)
                        .where(qAlertRel.alertId.eq(qAlert.id),
                                qAlertRel.operationCode.eq(OperationEntity.OP_CODE_DISABLE_UAS_AUTH),
                                qAlertRel.operationStat.eq(OperationEntity.OP_STAT_CANCEL))
                )).limit(20)
                .fetch();
        alerts.forEach(entity -> {
            System.out.println(entity);
        });

        /*
        QDepartment department = QDepartment.department;
        QDepartment d = new QDepartment("d");
        queryFactory.selectFrom(department)
            .where(department.size.eq(
                JPAExpressions.select(d.size.max()).from(d)))
            .fetch();
         */
        /*
        QEmployee employee = QEmployee.employee;
        QEmployee e = new QEmployee("e");
        queryFactory.selectFrom(employee)
            .where(employee.weeklyhours.gt(
                JPAExpressions.select(e.weeklyhours.avg())
                    .from(employee.department.employees, e)
                    .where(e.manager.eq(employee.manager))))
            .fetch();
         */
    }

    @Test
    @Ignore
    public void delete() {
        qFac.delete(qAlert).execute();
        qFac.delete(qAlert)
                .where(qAlert.alertStat.eq(SecEventAlertEntity.ALERT_STAT_DISMISSED))
                .execute();
    }

    @Test
    public void update() {
        Random random = new Random();
        EntityManager em = HibernateUtil.getJpaEntityManager();
        JPAQueryFactory factory = new JPAQueryFactory(em);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        factory.update(qAlert)
                .where(qAlert.alertStat.eq(SecEventAlertEntity.ALERT_STAT_DISMISSED))
                .set(qAlert.startTime,
                        new Timestamp(System.currentTimeMillis() + random.nextInt(100000)))
                .execute();
        transaction.commit();
        em.close();
    }

    @Test
    public void exposeJAPQuery() {
        Query jpaQuery = qFac.selectFrom(qAlert).limit(3).createQuery();
        List results = jpaQuery.getResultList();
        results.forEach(o -> {
            System.out.println(o);
        });
    }

    @Test
    public void complexQuery() {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qAlert.alertReason.notEqualsIgnoreCase(SecEventAlertEntity.ALERT_REASON_ROOT_VIOLATION));
        builder.and(qAlert.alertStat.eq(SecEventAlertEntity.ALERT_STAT_DISMISSED));

        List<Tuple> alerts =
                qFac.select(qAlert.alertReason, qAlert.alertStat, qAlert.targetName)
                        .from(qAlert).where(builder).limit(10).fetch();
        alerts.forEach(entity -> System.out.println(entity));
    }

    @Test
    public void caseWhenThen() {
        /*
        SELECT OrderID, Quantity,
        CASE
            WHEN Quantity > 30 THEN "The quantity is greater than 30"
            WHEN Quantity = 30 THEN "The quantity is 30"
            ELSE "The quantity is something else"
        END
        FROM OrderDetails;
         */
        Expression<String> alias = new CaseBuilder()
                .when(qAlert.alertReason.eq(SecEventAlertEntity.ALERT_REASON_ROOT_VIOLATION))
                .then("root violation")
                .when(qAlert.alertReason.eq(SecEventAlertEntity.ALERT_REASON_SIM_VIOLATION))
                .then("sim violation")
                .otherwise("other violation");
        List<Tuple> tuples = qFac.select(qAlert.targetName, qAlert.alertReason, alias)
                .from(qAlert).limit(10).fetch();
        tuples.forEach(tuple -> System.out.println(tuple));
    }

    @Test
    @Ignore
    public void constant() {
        /*
        List<SecEventAlertEntity> alerts = qFac.selectFrom(qAlert)
                .where(qAlert.alertReason.in(
       JPAExpressions.select(
               Expressions.constant(SecEventAlertEntity.ALERT_REASON_SIM_VIOLATION),
               Expressions.constant(SecEventAlertEntity.ALERT_REASON_LOST_VIOLATION)));
               */
    }

    @Test
    public void resultHandle() {
        List<Tuple> tuples = qFac.select(qAlert.targetName, qAlert.alertReason, qAlert.alertStat)
                .from(qAlert).limit(5).fetch();
        tuples.forEach(tuple -> {
            System.out.println("===============================");
            System.out.println(tuple.get(qAlert.targetName));
            System.out.println(tuple.get(qAlert.alertReason));
            System.out.println(tuple.get(qAlert.alertStat));
        });
    }

    @Test
    public void resultHandleWithBean() {
        List<FooBean> tuples = qFac.select(
                Projections.bean(FooBean.class, qAlert.targetName, qAlert.alertReason, qAlert.alertStat))
                .from(qAlert).limit(5).fetch();
        tuples.forEach(tuple -> {
            System.out.println("===============================");
            System.out.println(tuple.getTargetName());
            System.out.println(tuple.getAlertReason());
            System.out.println(tuple.getAlertStat());
        });
    }

    @Test
    public void aggregate() {
        Map<String, List<?>> map =
                qFac.from(qAlert, qAlertRel)
                .where(qAlert.id.eq(qAlertRel.alertId))
                .transform(groupBy(qAlert.targetName).as(list(qAlertRel)));
        map.forEach((s, objects) -> {
            System.out.println("=======================================");
            System.out.println(s);
            System.out.println(objects);
        });
    }
}