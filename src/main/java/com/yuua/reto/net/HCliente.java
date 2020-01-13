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

	public HCliente(Server server, TransaccionesHibernate transacciones) {
		this.server = server;
		this.socket = server.cliente;
		this.salida = server.salida;
		this.entrada = server.entrada;
		this.transacciones = transacciones;
	}

	@Override
	public void run() {
		try {
			Request peticion = (Request) entrada.readObject();
			switch (peticion.getCodigoPeticion()) {
			case 0:
				System.out.println("Peticion recibida, codigo: 0, matando hilo");
				server.borrarHilo(this);
				break;
			case 1:
				System.out.println("Peticion recibida, codigo: 1, contenido: " + peticion.getObjetoEnviado());
				break;
			case 60:
				Object[] datosPeticion = (Object[]) peticion.getObjetoEnviado();
				Object[] resultado = transacciones.consultarVariosObjetos((String) datosPeticion[0], (String[]) datosPeticion[1], (String[]) datosPeticion[2]);
				Gson parser = new Gson();
				String resultadoJson = parser.toJson(resultado);
				server.mandarRequest(new Request(61, resultadoJson), salida);
			default:
				break;
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null)
					socket.close();
				if (entrada != null)
					entrada.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
