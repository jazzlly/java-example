package com.infiniteskills.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static final EntityManagerFactory entityManagerFactory =
			Persistence.createEntityManagerFactory("infinite-finances");

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			// configuration.addAnnotatedClass(User.class);
			return configuration
					.buildSessionFactory(new StandardServiceRegistryBuilder()
							.applySettings(configuration.getProperties())
							.build());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"There was an error building the factory");
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static EntityManager getJpaEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static Connection getDbConnJava()
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ifinances",
				"root", "pekall1234");
	}
}
