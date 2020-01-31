package com.yuua.reto.conexionbd;

import com.yuua.reto.xml.Utilidades;

public class MainPruebas {

	public static void main(String[] args) {
//		Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
//		SessionFactory sf = conf.buildSessionFactory();
//		TransaccionesHibernate transacciones = new TransaccionesHibernate(sf);
		
		String cadena1 = "acb";
		String cadena2 = "abc";
		String[] cadenitas= {"bb","abc","abcd","abc","abcd"};
		//String ordenada=Utilidades.ordenar2String(cadena1, cadena2, 0);
		Utilidades.bubbleSort(cadenitas);
		for (String string : cadenitas) {
			System.out.println(string);
		}
	}

}
