package com.infiniteskills.data

import com.infiniteskills.data.entities.Bank
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ScriptUtils
import spock.lang.Specification

import java.sql.Connection

class HibernateSmokeTest extends Specification {

    void setup() {
        Connection connection= HibernateUtil.getDbConnJava()
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("populate.sql"))
        connection.close()
    }

    def "hello hibernate"() {
        given:
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession()
        Transaction transaction = session.beginTransaction()
        Bank bank = (Bank) session.get(Bank.class, 1L)

        when:
        bank.setName("Something Different")
        bank.setAddressLine1("Another Address Line")
        transaction.commit()

        then:
        def bank1 = session.get(Bank.class, 1L)
        assert bank1.name.equals(bank.name)
        assert bank1.addressLine1.equals(bank.addressLine1)

        cleanup:
        session.close()
        sessionFactory.close()
    }
}
