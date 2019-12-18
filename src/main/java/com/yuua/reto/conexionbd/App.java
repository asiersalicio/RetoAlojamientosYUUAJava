package com.yuua.reto.conexionbd;

import com.yuua.reto.xml.XMLControler;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		XMLControler xml =new XMLControler("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/alojamiento_de_euskadi/opendata/alojamientos.xml");
		
		xml.downloadNewXML();
		
		xml.toAlojamientoById(1);
//		Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
//		SessionFactory sf = conf.buildSessionFactory();
//		Session session = sf.openSession();
//
//		session.beginTransaction();
//
//		Manzana roja = new Manzana();
//		roja.setId(1);
//		roja.setPeso(20);
//		roja.setEspecie("Sovietica");
//
//		// Save the employee in database
//		session.save(roja);
//
//		// Commit the transaction
//		session.getTransaction().commit();
//
//		sf.close();
	}

}
