package com.yuua.reto.conexionbd;

import java.io.File;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainPruebas {

	public static void main(String[] args) {
		Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
		SessionFactory sf = conf.buildSessionFactory();
		TransaccionesHibernate transacciones = new TransaccionesHibernate(sf);
		transacciones.buscarAlojamientosPorFechasYLocalizacion("alo", new Date(), new Date());
	}

}
