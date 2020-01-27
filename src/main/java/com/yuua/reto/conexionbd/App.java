package com.yuua.reto.conexionbd;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.yuua.reto.net.Server;
import com.yuua.reto.xml.XMLControler;

public class App {

	public static void main(String[] args) {

		Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
		SessionFactory sf = conf.buildSessionFactory();
		TransaccionesHibernate transacciones = new TransaccionesHibernate(sf);

		// TO-DO CAMBIAR LUGAR DE ENLACE
		XMLControler xmlAlojamientos = new XMLControler("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/albergues_de_euskadi/opendata/alojamientos.xml", "Albergues", transacciones);
		XMLControler xmlAlbergues = new XMLControler("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/alojamientos_rurales_euskadi/opendata/alojamientos.xml", "Rurales", transacciones);
		XMLControler xmlCamping = new XMLControler("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/campings_de_euskadi/opendata/alojamientos.xml", "Camping", transacciones);

		xmlAlojamientos.downloadNewXML();
		xmlAlbergues.downloadNewXML();
		xmlCamping.downloadNewXML();

		transacciones.insertarRoot();

		if (xmlAlojamientos.isUpdateRequired()) {
//		if(true) {
			transacciones.cargarAlojamientos(xmlAlojamientos);
		}
		if (xmlAlbergues.isUpdateRequired()) {
//		if(true) {
			transacciones.cargarAlojamientos(xmlAlbergues);
		}
		if (xmlCamping.isUpdateRequired()) {
//		if(true) {
			transacciones.cargarAlojamientos(xmlCamping);
		}

		Server servidor = new Server(transacciones);
		Thread hiloserver = new Thread(servidor);
		hiloserver.start();
	}

}
