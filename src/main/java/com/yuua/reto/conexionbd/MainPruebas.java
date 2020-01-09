package com.yuua.reto.conexionbd;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.yuua.reto.entidades.Alojamiento;
import com.yuua.reto.net.Server;

public class MainPruebas {

	public static void main(String[] args) {

		Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
		SessionFactory sf = conf.buildSessionFactory();
		TransaccionesHibernate transacciones = new TransaccionesHibernate(sf);
		
		
		transacciones.consultarVariosObjetos(Alojamiento.class, new String[]{"nombre","telefono"}, new String[]{"A ROOM IN THE CITY","943424589"});
		transacciones.consultarById(Alojamiento.class, 1);
		Server servidor = new Server(transacciones);
		Thread hiloserver = new Thread(servidor);
		hiloserver.start();
	}

}
