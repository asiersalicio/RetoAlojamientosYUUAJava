package com.yuua.reto.conexionbd;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yuua.reto.entidades.Alojamiento;
import com.yuua.reto.entidades.Municipio;
import com.yuua.reto.entidades.Pais;
import com.yuua.reto.entidades.Territorio;
import com.yuua.reto.xml.XMLControler;

public class TransaccionesHibernate {

	private SessionFactory factory = null;

	public TransaccionesHibernate(SessionFactory factory) {
		super();
		this.factory = factory;
	}

	public void insertarPaisesTerritoriosMunicipios(XMLControler controlador) {
		Session session = factory.openSession();
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

	public void cargarAlojamientos(XMLControler controlador) {
		Session session = factory.openSession();
		session.beginTransaction();
		for (int i = 0; i < controlador.getSize(); i++) {
			Alojamiento aloj = controlador.toAlojamientoById(i, session);
			Alojamiento alojbd = (Alojamiento) session.createQuery("FROM Alojamiento WHERE nombre = '"+aloj.getNombre()+"'").uniqueResult();			
			
			if (alojbd == null) {
				session.saveOrUpdate(aloj);
			}
		}
		session.getTransaction().commit();
		session.clear();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public Object consultarObjeto(@SuppressWarnings("rawtypes") Class clase, Object id) {
		Session session = factory.openSession();
		Object objeto = session.get(clase, (Serializable) id);
		return objeto;
	}
}
