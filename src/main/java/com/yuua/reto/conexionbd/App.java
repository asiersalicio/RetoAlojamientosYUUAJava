package com.yuua.reto.conexionbd;

import com.yuua.reto.entidades.Municipio;
import com.yuua.reto.entidades.Pais;
import com.yuua.reto.entidades.Territorio;
import com.yuua.reto.xml.XMLControler;


public class App {
	
	public Pais pais;
	public Territorio territorio;
	public Municipio municipio;
	
	public static void main(String[] args) {

		
		XMLControler xmlAlojamientos =new XMLControler("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/alojamiento_de_euskadi/opendata/alojamientos.xml", "Alojamientos");
		XMLControler xmlAlbergues =new XMLControler("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/albergues_de_euskadi/opendata/alojamientos.xml", "Albergues");
		XMLControler xmlCamping = new XMLControler("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/campings_de_euskadi/opendata/alojamientos.xml", "Camping");
		
		xmlAlojamientos.downloadNewXML();
		xmlAlbergues.downloadNewXML();
		xmlCamping.downloadNewXML();
		
		
		System.out.println(xmlAlojamientos.getSize());
		xmlAlojamientos.toAlojamientoById(1);
		
		System.out.println(xmlAlbergues.getSize());
		xmlAlbergues.toAlojamientoById(1);		
		
		System.out.println(xmlCamping.getSize());
		xmlCamping.toAlojamientoById(1);
				
		
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
