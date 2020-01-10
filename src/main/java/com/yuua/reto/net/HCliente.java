package com.yuua.reto.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.yuua.reto.conexionbd.TransaccionesHibernate;


public class HCliente implements Runnable{
	private Socket socket = null;
	public ObjectOutputStream salida = null;
	private ObjectInputStream entrada = null;
	private TransaccionesHibernate transacciones;
	private Server server;

	public HCliente(Server server,TransaccionesHibernate transacciones) {
		this.server = server;
		this.socket = server.cliente;
		this.salida = server.salida;
		this.entrada = server.entrada;
		this.transacciones=transacciones;
	}

	@Override
	public void run() {
		boolean condicion=true;
		while (condicion) {
			try {
				Request peticion = (Request) entrada.readObject();				
					switch (peticion.getCodigoPeticion()) {
					case 0:
						System.out.println("Peticion recibida, codigo: 0, matando hilo");
						server.borrarHilo(this);
						condicion=false;
						break;
					case 1:
						System.out.println("Peticion recibida, codigo: 1, contenido: "+peticion.getObjetoEnviado());
						break;
					case 60:
						Object[] datosPeticion=(Object[]) peticion.getObjetoEnviado();
						Object[] resultado=transacciones.consultarVariosObjetos((String)datosPeticion[0], (String[])datosPeticion[1],(String[]) datosPeticion[2]);
						Gson parser=new Gson();
						String resultadoJson=parser.toJson(resultado);
						server.mandarRequest(new Request(61, resultadoJson), salida);
					default:
						break;
					}
				
			} catch (SocketException e) {
				e.printStackTrace();
				condicion=false;
				
			} catch (IOException e) {
				e.printStackTrace();
				condicion=false;
	
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				condicion=false;

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
