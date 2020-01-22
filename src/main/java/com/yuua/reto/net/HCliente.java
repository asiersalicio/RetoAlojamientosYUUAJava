package com.yuua.reto.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

import com.google.gson.Gson;
import com.yuua.reto.conexionbd.TransaccionesHibernate;
import com.yuua.reto.entidades.Alojamiento;
import com.yuua.reto.entidades.Municipio;

public class HCliente implements Runnable {
	private Socket socket = null;
	public ObjectOutputStream salida = null;
	private ObjectInputStream entrada = null;
	private TransaccionesHibernate transacciones;
	private Server server;

	public HCliente(Server server, TransaccionesHibernate transacciones, Socket simismo) {
		this.server = server;
		this.socket = simismo;
		try {
			this.salida = new ObjectOutputStream(socket.getOutputStream());
			this.entrada = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.transacciones = transacciones;
	}

	@Override
	public void run() {
		try {
			Request peticion = (Request) entrada.readObject();
			Object[] datosPeticion, resultado;
			String resultadoJson;
			boolean resultadoBoolean;
			Gson parser;
			switch (peticion.getCodigoPeticion()) {
			// Consultar alojamientos disponibles entre fechas en localizacion especifica
			case 20:
				datosPeticion = (Object[]) peticion.getObjetoEnviado();
				resultado = transacciones.buscarAlojamientosPorFechasYLocalizacion((Municipio) datosPeticion[0], (Date) datosPeticion[1], (Date) datosPeticion[2]);
				parser = new Gson();
				resultadoJson = parser.toJson(resultado);
				server.mandarRequest(new Request(21, resultadoJson), salida);
				break;
			// Consultar alojamientos disponibles entre fechas
			case 30:
				datosPeticion = (Object[]) peticion.getObjetoEnviado();
				resultado = transacciones.buscarAlojamientosPorFechas((Date) datosPeticion[0], (Date) datosPeticion[1]);
				parser = new Gson();
				resultadoJson = parser.toJson(resultado);
				server.mandarRequest(new Request(31, resultadoJson), salida);
				break;
			// Consultar disponibilidad alojamiento entre fechas - con id
			case 40:
				datosPeticion = (Object[]) peticion.getObjetoEnviado();
				resultadoBoolean = transacciones.consultarAlojamientoPorFechas((Alojamiento) datosPeticion[0], (Date) datosPeticion[1], (Date) datosPeticion[2]);
				server.mandarRequest(new Request(41, resultadoBoolean), salida);
				break;
			// Consulta con parametros
			case 60:
				datosPeticion = (Object[]) peticion.getObjetoEnviado();
				resultado = transacciones.consultarVariosObjetos((String) datosPeticion[0], (String[]) datosPeticion[1], (String[]) datosPeticion[2], false);
				parser = new Gson();
				resultadoJson = parser.toJson(resultado);
				server.mandarRequest(new Request(61, resultadoJson), salida);
				break;
			// Consulta con parametros LIKE
			case 65:
				datosPeticion = (Object[]) peticion.getObjetoEnviado();
				resultado = transacciones.consultarVariosObjetos((String) datosPeticion[0], (String[]) datosPeticion[1], (String[]) datosPeticion[2], true);
				parser = new Gson();
				resultadoJson = parser.toJson(resultado);
				server.mandarRequest(new Request(61, resultadoJson), salida);
				break;
			// Insert
			case 50:
				Object[] datos = (Object[]) peticion.getObjetoEnviado();
				boolean resbool = transacciones.insertarObjeto(datos[0], (String) datos[1]);
				server.mandarRequest(new Request(51, resbool), salida);
				break;
			case 70:

				break;
			default:
				break;
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void mandarPeticion(Request req) {
		try {
			this.salida.writeObject(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
