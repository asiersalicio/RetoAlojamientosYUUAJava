package com.yuua.reto.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.yuua.reto.conexionbd.TransaccionesHibernate;

public class Server implements Runnable {

	private final int PUERTO = 55555;
	public ServerSocket servidor = null;
	public TransaccionesHibernate transaccionesHibernate;
	public ArrayList<String> usuarios;

	public Server(TransaccionesHibernate transacciones) {
		this.transaccionesHibernate=transacciones;
	}

	@Override
	public void run() {
		servidor = null;
		try {
			servidor = new ServerSocket(PUERTO);
			while (true) {
				System.out.println("Esperando conexiones del cliente...");
				Socket cliente = servidor.accept();
				
				System.out.println("Conexion realizada");
				HCliente hiloCliente = new HCliente(this,this.transaccionesHibernate,cliente);
				Thread threadCliente = new Thread(hiloCliente);
				threadCliente.start();
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
			e.printStackTrace();
		}
	}
}
