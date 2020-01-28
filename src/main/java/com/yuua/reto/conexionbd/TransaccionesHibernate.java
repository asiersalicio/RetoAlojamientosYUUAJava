package com.yuua.reto.conexionbd;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuua.reto.entidades.Alojamiento;
import com.yuua.reto.entidades.Localizacion;
import com.yuua.reto.entidades.Reserva;
import com.yuua.reto.entidades.Usuario;
import com.yuua.reto.xml.XMLControler;

import logs.Logger;

public class TransaccionesHibernate {

	private SessionFactory factory = null;

	public TransaccionesHibernate(SessionFactory factory) {
		super();
		this.factory = factory;
	}

	public void cargarAlojamientos(XMLControler controlador) {
		for (int i = 0; i < controlador.getSize(); i++) {
			Alojamiento aloj = controlador.toAlojamientoById(i);
			if (aloj != null) {
				Alojamiento alojbd;
				try {
					alojbd = (Alojamiento) ejecutarQuery(crearQuery("Alojamiento", new String[] { "documentname","turismdescription" }, new String[] { aloj.getNombre(),aloj.getDescripcion() }))[0];
				} catch (IndexOutOfBoundsException e) {
					alojbd = null;
				}
				if (alojbd == null) {
					insertarObjeto(aloj);
				}
			}
		}
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

	public boolean insertarObjetosJson(Object nombre, String objetos) {
		try {
			Gson gson = new Gson();
			List<?> list = new ArrayList<>();
			switch ((String) nombre) {
			case "Alojamiento":
				list = gson.fromJson(objetos, new TypeToken<List<Alojamiento>>() {
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
			case "Usuario":
				list = gson.fromJson(objetos, new TypeToken<List<Usuario>>() {
				}.getType());
				break;
			}
			for (Object item : list) {
				insertarObjeto(item);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void insertarObjeto(Object objeto) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(objeto);		
		session.flush();
		session.getTransaction().commit();
		session.clear();
		session.close();
	}

	public void insertarRoot() {
		Session session = factory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(new Usuario("", null, null, "administrador", "root", "202cb962ac59075b964b07152d234b70", null, null, 456845845));
		session.flush();
		session.getTransaction().commit();
		session.clear();
		session.close();
	}

	public boolean consultarAlojamientoPorFechas(Alojamiento aloj, Date fecha1, Date fecha2) {
		Object[] objetos = null;
		int id = aloj.getId();
		String query = "from Alojamiento as aloj,Reserva as res where aloj.id not in(" + "select r.alojamiento.id " + "from Reserva as r " + "where (r.fechaEntrada between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + ") OR (r.fechaSalida between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + ")) and res.alojamiento.id = '" + id + "'";
		objetos = ejecutarQuery(query);
		if (objetos.length == 0 || objetos == null) {
			return false;
		} else {
			return true;
		}
	}

	public Object[] buscarAlojamientosPorFechasYLocalizacion(String municipio, Date fecha1, Date fecha2) {
		Object[] objetos = null;
		String query = "from Alojamiento as aloj where aloj.localizacion.tmunicipio='" + municipio + "' and aloj.id not in(select r.alojamiento.id from Reserva as r where (r.fechaEntrada between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + ") OR (r.fechaSalida between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + "))";
		objetos = ejecutarQuery(query);
		return objetos;
	}

	public Object[] buscarAlojamientosReservadosPorPersona(String idDni) {
		Object[] objetos = null;
		String query = "from Reserva as res where idDni ='"+idDni+"'";
		objetos = ejecutarQuery(query);
		return objetos;
	}
	
	public Object[] buscarAlojamientosPorFechas(Date fecha1, Date fecha2) {
		Object[] objetos = null;
		String query = "from Alojamiento as aloj where aloj.id not in(select r.alojamiento.id from Reserva as r where (r.fechaEntrada between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + ") OR (r.fechaSalida between " + formatFchSql(fecha1) + " and " + formatFchSql(fecha2) + "))";
		objetos = ejecutarQuery(query);
		return objetos;
	}
	
	public Object[] buscarMunicipiosDistinct(String nombre) {
		Object[] objetos = null;
		String query = "select distinct loc.tmunicipio from Localizacion as loc where lower(loc.tmunicipio) LIKE '%"+nombre+"%'";
		objetos = ejecutarQuery(query);
		return objetos;
	}

	private String formatFchSql(Date fecha) {
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
		return df.format(fecha);
	}

	public String crearQuery(String clase, String[] campos, String[] condiciones) {
		String query = "FROM " + clase;
		if (condiciones.length > 0) {
			query += " WHERE ";
		}
		for (int i = 0; i < condiciones.length; i++) {	
			query += "lower("+campos[i] + ") = '" + condiciones[i].toLowerCase() + "'";
			if (i < condiciones.length - 1) {
				query += " AND ";
			}
		}
		return query;
	}

	public String crearQueryLike(String clase, String[] campos, String[] condiciones) {
		String query = "FROM " + clase;
		if (condiciones.length > 0) {
			query += " WHERE ";
		}
		for (int i = 0; i < condiciones.length; i++) {			
			query += "lower(" + campos[i] + ") LIKE '%" + condiciones[i].toLowerCase() + "%'";
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
	public Object[] consultarVariosObjetos(String clase, String[] campos, String[] condiciones, boolean like) {
		if (campos.length == condiciones.length) {
			Object[] objetos = null;
			String query;
			if (like) {
				query = crearQueryLike(clase, campos, condiciones);
			} else {
				query = crearQuery(clase, campos, condiciones);
			}
			objetos = ejecutarQuery(query);
			return objetos;
		} else {
			return null;
		}
	}

	public Object[] ejecutarQuery(String query) {
		Session session = factory.openSession();
		session.beginTransaction();
		Object[] objetos = null;
		org.hibernate.query.Query<?> queryHbn = session.createQuery(query);
		queryHbn.setMaxResults(20);
		try {
			objetos = queryHbn.getResultList().toArray();
		} catch (EntityNotFoundException | ObjectNotFoundException e) {
			Logger.getInstance().loggear("Objeto no econtrado " + e.getMessage(), this.getClass(), 0);
			return null;
		} finally {
			session.flush();
			session.getTransaction().commit();
			session.clear();
			session.close();
		}
		return objetos;
	}
}
