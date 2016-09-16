package com.M2I.ecommerce.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.M2I.ecommerce.beans.Client;

public class InitDBTest {

	public static void clientVide() {
		Client client = new Client();
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		session.save(client);
		t.commit();
		session.close();
	}
}
