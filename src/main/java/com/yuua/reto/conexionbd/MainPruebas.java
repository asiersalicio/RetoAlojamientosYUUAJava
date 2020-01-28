package com.yuua.reto.conexionbd;

import java.io.File;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.yuua.reto.xml.Utilidades;

public class MainPruebas {

	public static void main(String[] args) {
		Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
		SessionFactory sf = conf.buildSessionFactory();
		TransaccionesHibernate transacciones = new TransaccionesHibernate(sf);
		
		String aux = Utilidades.quitarFormateo("A room in the city es un hostal que permite a sus huéspedes conocer de cerca los encantos de la ciudad de <a href=&quot;https://turismo.euskadi.eus/es/top10/localidades/donostia-san-sebastian/aa30-12376/es/&quot;>San Sebasti&aacute n</a>, al tiempo que sirve como punto de encuentro para personas viajeras. De hecho, se encuentra en el centro de la ciudad, en un antiguo convento hoy convertido en un multiespacio con una efervescente actividad cultural, el Convent Garden.</p>&nbsp </p>De este modo, tras saborear la gastronom&iacute a local, tomar el sol en la playa, salir a surfear o divertirse en la Parte Vieja de San Sebasti&aacute n, sus huéspedes podr&aacute n acomodarse en sus espacios comunes tanto de exterior como de interior a charlar y conocer gente, probar los platos de sus restaurantes o prepar&aacute rsela personalmente en la cocina com&uacute n o disfrutar de las actividades culturales que tienen lugar en los espacios del edificio.</p>");
		System.out.println(aux);
		System.out.println(Utilidades.quitarTags(aux));
	}

}
