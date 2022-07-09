package com.infiniteskills.data;

import com.infiniteskills.data.model.QAlertOperationRelEntity;
import com.infiniteskills.data.model.QSecEventAlertEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;

public class TestBase {
    protected static SessionFactory sessionFactory =
            HibernateUtil.getSessionFactory();
    protected static EntityManagerFactory entityManagerFactory =
            HibernateUtil.getEntityManagerFactory();

    protected static QSecEventAlertEntity qAlert = QSecEventAlertEntity.secEventAlertEntity;
    protected static QAlertOperationRelEntity qAlertRel = QAlertOperationRelEntity.alertOperationRelEntity;

    protected static JPAQueryFactory qFac = new JPAQueryFactory(HibernateUtil.getJpaEntityManager());

}
