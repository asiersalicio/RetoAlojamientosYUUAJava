package com.yuua.reto.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.yuua.reto.conexionbd.TransaccionesHibernate;

public class Server implements Runnable {

	private final int PUERTO = 55555;
	public ServerSocket servidor = null;
	public ArrayList<HCliente> conexiones;
	//public Socket cliente = null;
	public ObjectInputStream entrada = null;
	public ObjectOutputStream salida = null;
	public TransaccionesHibernate transaccionesHibernate;
	public ArrayList<String> usuarios;

	public Server(TransaccionesHibernate transacciones) {
		this.transaccionesHibernate=transacciones;
	}

	@Override
	public void run() {
		servidor = null;
		entrada = null;
		salida = null;
		try {
			servidor = new ServerSocket(PUERTO);
			conexiones = new ArrayList<HCliente>();
			while (true) {
				System.out.println("Esperando conexiones del cliente...");
				Socket cliente = servidor.accept();
				System.out.println("Conexion realizada");
				salida = new ObjectOutputStream(cliente.getOutputStream());
				entrada = new ObjectInputStream(cliente.getInputStream());
	
				HCliente hiloCliente = new HCliente(this,this.transaccionesHibernate,cliente);
				Thread threadCliente = new Thread(hiloCliente);
				threadCliente.start();
				conexiones.add(hiloCliente);
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (servidor != null)
					servidor.close();
				if (entrada != null)
					entrada.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Fin servidor");
		}
	}

	public void mandarRequest(Request peticion, ObjectOutputStream salida) {
		try {
			salida.writeObject(peticion);
		} catch (IOException e) {
		}
	}
	

	public void broadcast(Request req) {
		if (conexiones.size() > 0) {
			for (HCliente hilo : conexiones) {
				hilo.mandarPeticion(req);
			}
		}
	}

	public void borrarHilo(HCliente hiloCliente) {
		HCliente hilo = null;
		for (int i = 0; i < conexiones.size(); i++) {
			if (conexiones.get(i).equals(hiloCliente)) {
				hilo = conexiones.get(i);
			}
		}
		if (hilo != null) {
			conexiones.remove(hilo);
		}
	}

	public void apagarServidor() {
		for (int i = 0; i < conexiones.size(); i++) {
			mandarRequest(new Request(0, null), conexiones.get(i).salida);
		}
	}
}
