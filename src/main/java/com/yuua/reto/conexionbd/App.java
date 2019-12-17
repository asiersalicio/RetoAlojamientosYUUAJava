package com.yuua.reto.conexionbd;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();

		session.beginTransaction();

		Manzana roja = new Manzana();
		roja.setId(1);
		roja.setPeso(20);
		roja.setEspecie("Sovietica");

		// Save the employee in database
		session.save(roja);

		// Commit the transaction
		session.getTransaction().commit();

		sf.close();
	}

}
