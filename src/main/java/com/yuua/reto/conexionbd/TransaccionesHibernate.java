package com.yuua.reto.conexionbd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yuua.reto.entidades.Municipio;
import com.yuua.reto.entidades.Pais;
import com.yuua.reto.entidades.Territorio;
import com.yuua.reto.xml.XMLControler;

public class TransaccionesHibernate {

	public void insertarPaisesTerritoriosMunicipios(SessionFactory sf, XMLControler controlador) {
		Session session = sf.openSession();
		session.beginTransaction();
		for (Object pais : controlador.buscarElementos("countrycode", "country", Pais.class)) {
			if (session.get(Pais.class, ((Pais) pais).getId()) == null) {
				session.saveOrUpdate(pais);
			}
		}

		for (Object municipio : controlador.buscarElementos("municipalitycode", "municipality", Municipio.class)) {
			if (session.get(Municipio.class, ((Municipio) municipio).getId()) == null) {
				session.saveOrUpdate(municipio);
			}
		}

		for (Object territorio : controlador.buscarElementos("territorycode", "territory", Territorio.class)) {
			if (session.get(Territorio.class, ((Territorio) territorio).getId()) == null) {
				session.saveOrUpdate(territorio);
			}
		}
		session.getTransaction().commit();
		session.clear();
		session.close();
	}

	public void cargarAlojamientos(SessionFactory sf, XMLControler controlador) {
		Session session = sf.openSession();
		session.beginTransaction();
		for (int i = 0; i < controlador.getSize(); i++) {
			session.save(controlador.toAlojamientoById(i, session));
		}
		session.getTransaction().commit();
		session.clear();
		session.close();
	}
}
