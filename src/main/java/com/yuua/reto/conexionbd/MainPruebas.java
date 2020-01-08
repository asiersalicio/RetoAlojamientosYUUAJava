package com.yuua.reto.conexionbd;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.yuua.reto.net.Server;

public class MainPruebas {

	public static void main(String[] args) {

		Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
		SessionFactory sf = conf.buildSessionFactory();
		TransaccionesHibernate transacciones = new TransaccionesHibernate(sf);
		
		Server servidor = new Server(transacciones);
		Thread hiloserver = new Thread(servidor);
		hiloserver.start();
	}

}
