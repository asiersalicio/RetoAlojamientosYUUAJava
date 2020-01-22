package com.yuua.reto.conexionbd;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuua.reto.entidades.Alojamiento;
import com.yuua.reto.entidades.Localizacion;
import com.yuua.reto.entidades.Municipio;
import com.yuua.reto.entidades.Pais;
import com.yuua.reto.entidades.Reserva;
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
			Alojamiento alojbd = (Alojamiento) session.createQuery("FROM Alojamiento WHERE nombre = '" + aloj.getNombre() + "' AND descripcion='" + aloj.getDescripcion() + "'").uniqueResult();
			Localizacion loctemp = aloj.getLocalizacion();
			if (alojbd == null && (loctemp.getTpais() != null && loctemp.getTmunicipio() != null && loctemp.getTterritorio() != null)) {
				session.saveOrUpdate(aloj);
			}
		}
		session.getTransaction().commit();
		session.clear();
		session.close();
	}

	/**
	 * Consultar un unico objeto por id
	 * 
	 * @param clase la clase del objeto que quieres buscar
	 * @param id    el id del objeto buscado, debe ser el mismo tipo (char[],
	 *              string, int, etc...)
	 * @return un unico objeto de la clase especificada Codigo: 50
	 */

	public Object consultarById(Class<?> clase, Serializable id) {
		Session session = factory.openSession();
		Object objeto = null;
		objeto = session.get(clase, id);
		session.clear();
		session.close();
		return objeto;
	}

	public boolean insertarObjeto(Object nombre, String objetos) {
		try {
			Gson gson = new Gson();

			List<?> list = new ArrayList<>();

			switch ((String) nombre) {
			case "Alojamiento":
				list = gson.fromJson(objetos, new TypeToken<List<Alojamiento>>() {
				}.getType());
				break;
			case "Pais":
				list = gson.fromJson(objetos, new TypeToken<List<Pais>>() {
				}.getType());
				break;
			case "Territorio":
				list = gson.fromJson(objetos, new TypeToken<List<Territorio>>() {
				}.getType());
				break;
			case "Municipio":
				list = gson.fromJson(objetos, new TypeToken<List<Municipio>>() {
				}.getType());
				break;
			case "Localizacion":
				list = gson.fromJson(objetos, new TypeToken<List<Localizacion>>() {
				}.getType());
				break;
			case "Reserva":
				list = gson.fromJson(objetos, new TypeToken<List<Reserva>>() {
				}.getType());
				break;
			}

			Session session = factory.openSession();
			session.beginTransaction();
			for (Object item : list) {
				session.save(item);
			}
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public boolean consultarAlojamientoPorFechas(Alojamiento aloj, Date fecha1, Date fecha2) {
		Session session = factory.openSession();
		Object[] objetos = null;
		int id = aloj.getId();
		String query = "from Alojamiento as aloj,Reserva as res where aloj.id not in(" + "select r.alojamiento.id " + "from Reserva as r " + "where (r.fechaEntrada between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + ") OR (r.fechaSalida between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + ")) and res.alojamiento.id = '" + id + "'";
		org.hibernate.query.Query<?> queryHbn = session.createQuery(query);
		queryHbn.setMaxResults(20);
		objetos = queryHbn.getResultList().toArray();
		if (objetos.length == 0 || objetos == null) {
			return false;
		} else {
			return true;
		}
	}

	public Object[] buscarAlojamientosPorFechasYLocalizacion(Municipio municipio, Date fecha1, Date fecha2) {
		Session session = factory.openSession();
		Object[] objetos = null;
		String query = "from Alojamiento as aloj where aloj.localizacion.tmunicipio.id='" + new String(municipio.getId()) + "' and aloj.id not in(select r.alojamiento.id from Reserva as r where (r.fechaEntrada between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + ") OR (r.fechaSalida between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + "))";
		org.hibernate.query.Query<?> queryHbn = session.createQuery(query);
		queryHbn.setMaxResults(20);
		objetos = queryHbn.getResultList().toArray();
		return objetos;
	}

	public Object[] buscarAlojamientosPorFechas(Date fecha1, Date fecha2) {
		Session session = factory.openSession();
		Object[] objetos = null;
		String query = "from Alojamiento as aloj where aloj.id not in(select r.alojamiento.id from Reserva as r where (r.fechaEntrada between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + ") OR (r.fechaSalida between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + "))";
		org.hibernate.query.Query<?> queryHbn = session.createQuery(query);
		queryHbn.setMaxResults(20);
		objetos = queryHbn.getResultList().toArray();
		return objetos;
	}

	private String formatFchSql(Date fecha) {
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
		return df.format(fecha);
	}

	private String sacarQuery(String clase, String[] campos, String[] condiciones) {
		String query = "FROM " + clase;
		for (int i = 0; i < condiciones.length; i++) {
			if (condiciones.length > 0) {
				query += " WHERE ";
			}
			query += campos[i] + " = '" + condiciones[i].toLowerCase() + "'";
			if (i < condiciones.length - 1) {
				query += " AND ";
			}
		}
		return query;
	}
	
	private String sacarQueryLike(String clase, String[] campos, String[] condiciones) {
		String query = "FROM " + clase;
		for (int i = 0; i < condiciones.length; i++) {
			if (condiciones.length > 0) {
				query += " WHERE ";
			}
			query += "lower("+campos[i] + ") LIKE '%" + condiciones[i].toLowerCase() + "%'";
			if (i < condiciones.length - 1) {
				query += " AND ";
			}
		}
		return query;
	}

	/**
	 * Metodo para buscar elementos en la base de datos por campos
	 * 
	 * @param clase       La clase del objeto que quieres buscar, debe estar mapeado
	 *                    en hibernate
	 * @param campos      los campos por los cuales se realiza la busqueda
	 * @param condiciones la condiciones que se deben cumplir con los campos, estos
	 *                    dos arrays deben de tener la misma longitud, de lo
	 *                    contrario devuelve null
	 * @return una lista de todos los objetos encontrados Ex:
	 *         consultarVariosObjetos(Alojamiento.class, new
	 *         String[]{"nombre","telefono"}, new String[]{"A ROOM IN THE
	 *         CITY","943424589"}); Res: los alojamientos con nombre "A ROOM IN THE
	 *         CITY" y telefono "943424589" Codigo:60
	 */
	public Object[] consultarVariosObjetos(String clase, String[] campos, String[] condiciones,boolean like) {
		if (campos.length == condiciones.length) {
			Session session = factory.openSession();
			Object[] objetos = null;
			String query;
			if (like) {
				query = sacarQueryLike(clase, campos, condiciones);
			}else {
				query = sacarQuery(clase, campos, condiciones);	
			}
			session.beginTransaction();
			org.hibernate.query.Query<?> queryHbn = session.createQuery(query);
			queryHbn.setMaxResults(20);
			objetos = queryHbn.getResultList().toArray();
			session.getTransaction().commit();
			session.clear();
			session.close();
			return objetos;
		} else {
			return null;
		}
	}
}
