package com.yuua.reto.conexionbd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		Manzana roja=new Manzana(0, 20, "Sovietica");
		Configuration conf = new Configuration().configure().addAnnotatedClass(Manzana.class);
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr =  session.beginTransaction();
		session.save(roja);
		tr.commit();

	}
}
