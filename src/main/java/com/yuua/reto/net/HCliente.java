package com.yuua.reto.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.google.gson.Gson;
import com.yuua.reto.conexionbd.TransaccionesHibernate;

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
			switch (peticion.getCodigoPeticion()) {
			case 60:
				Object[] datosPeticion = (Object[]) peticion.getObjetoEnviado();
				Object[] resultado = transacciones.consultarVariosObjetos((String) datosPeticion[0], (String[]) datosPeticion[1], (String[]) datosPeticion[2]);
				Gson parser = new Gson();
				String resultadoJson = parser.toJson(resultado);
				server.mandarRequest(new Request(61, resultadoJson), salida);
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
