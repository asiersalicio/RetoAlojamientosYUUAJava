package com.yuua.reto.conexionbd;

import java.io.Serializable;
import java.util.ArrayList;
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
			Alojamiento alojbd = (Alojamiento) session.createQuery("FROM Alojamiento WHERE nombre = '" + aloj.getNombre() + "' AND descripcion='"+aloj.getDescripcion()+"'").uniqueResult();

			if (alojbd == null) {
				session.saveOrUpdate(aloj);
			}
		}
		session.getTransaction().commit();
		session.clear();
		session.close();
	}

	/**
	 * Consultar un unico objeto por id
	 * @param clase la clase del objeto que quieres buscar 
	 * @param id el id del objeto buscado, debe ser el mismo tipo (char[], string, int, etc...)
	 * @return un unico objeto de la clase especificada
	 * Codigo: 50
	 */
	@SuppressWarnings("unchecked")
	public Object consultarById(@SuppressWarnings("rawtypes") Class clase, Serializable id) {
		Session session = factory.openSession();
		Object objeto = null;
		try {
			objeto = session.get(clase, id);
		} catch (Exception e) {
			return null;
		}
		return objeto;
	}

	
	/**
	 * Metodo para buscar elementos en la base de datos por campos
	 * @param clase La clase del objeto que quieres buscar, debe estar mapeado en hibernate
	 * @param campos los campos por los cuales se realiza la busqueda 
	 * @param condiciones la condiciones que se deben cumplir con los campos, estos dos arrays deben de tener la misma longitud, de lo contrario devuelve null
	 * @return una lista de todos los objetos encontrados
	 * Ex: consultarVariosObjetos(Alojamiento.class, new String[]{"nombre","telefono"}, new String[]{"A ROOM IN THE CITY","943424589"});
	 * Res: los alojamientos con nombre "A ROOM IN THE CITY" y telefono "943424589"
	 * Codigo:60
	 */
	public Object[] consultarVariosObjetos(@SuppressWarnings("rawtypes") String clase, String[] campos, String[] condiciones) {
		if (campos.length == condiciones.length) {
			Session session = factory.openSession();
			Object[] objetos = null;
			String query = "FROM " + clase;
			for (int i = 0; i < condiciones.length; i++) {
				if(condiciones.length>0) {
					query+=" WHERE ";
				}
				query += campos[i] + " = '" + condiciones[i] + "'";
				if (i < condiciones.length - 1) {
					query += " AND ";
				}
			}
			session.beginTransaction();
			org.hibernate.query.Query<?> queryHbn= session.createQuery(query);
			queryHbn.setMaxResults(20);
			objetos = queryHbn.getResultList().toArray();
			return objetos;
		} else {
			return null;
		}
	}

}
