package com.yuua.reto.conexionbd;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.yuua.reto.xml.XMLControler;

public class App {

	public static void main(String[] args) {

		XMLControler xmlAlojamientos = new XMLControler("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/alojamiento_de_euskadi/opendata/alojamientos.xml", "Alojamientos");
		XMLControler xmlAlbergues = new XMLControler("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/albergues_de_euskadi/opendata/alojamientos.xml", "Albergues");
		XMLControler xmlCamping = new XMLControler("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/campings_de_euskadi/opendata/alojamientos.xml", "Camping");

		xmlAlojamientos.downloadNewXML();
		xmlAlbergues.downloadNewXML();
		xmlCamping.downloadNewXML();

		Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
		SessionFactory sf = conf.buildSessionFactory();
		TransaccionesHibernate transacciones = new TransaccionesHibernate();

		transacciones.insertarPaisesTerritoriosMunicipios(sf, xmlAlojamientos);
		transacciones.insertarPaisesTerritoriosMunicipios(sf, xmlAlbergues);
		transacciones.insertarPaisesTerritoriosMunicipios(sf, xmlCamping);
		
		transacciones.cargarAlojamientos(sf, xmlAlojamientos);
		transacciones.cargarAlojamientos(sf, xmlAlbergues);
		transacciones.cargarAlojamientos(sf, xmlCamping);

		sf.close();
	}

}
