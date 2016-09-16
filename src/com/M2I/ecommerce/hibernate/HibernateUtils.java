package com.M2I.ecommerce.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtils {
	private static SessionFactory sessionFactory;

	// Crée une unique instance de la SessionFactory à partir de
	// hibernate.cfg.xml
	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (HibernateException ex) {
			throw new RuntimeException("Problème de configuration : " + ex.getMessage(), ex);
		}
	}

	public static void HibernateTest() {
		try {
			sessionFactory = new AnnotationConfiguration().configure()
					.setProperty("hibernate.connection.url", "jdbc:mysql://10.110.10.32:3306/ecommerceTest")
					.buildSessionFactory();
		} catch (HibernateException ex) {
			throw new RuntimeException("Problème de configuration : " + ex.getMessage(), ex);
		}
	}

	// Renvoie une session Hibernate
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
}
