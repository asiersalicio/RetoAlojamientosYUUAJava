package com.yuua.reto.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	private final int PUERTO = 55555;
	public ServerSocket servidor = null;
	public Socket cliente = null;
	public ObjectInputStream entrada = null;
	public ObjectOutputStream salida = null;

	public Server() {
	}

	@Override
	public void run() {
		servidor = null;
		cliente = null;
		entrada = null;
		salida = null;
		try {
			servidor = new ServerSocket(PUERTO);

			while (true) {
				System.out.println("Esperando conexiones del cliente...");
				cliente = servidor.accept();
				System.out.println("Conexion realizada");
				salida = new ObjectOutputStream(cliente.getOutputStream());
				entrada = new ObjectInputStream(cliente.getInputStream());
				Request peticion = (Request) entrada.readObject();
				
				switch (peticion.getCodigoPeticion()) {
				case 0:
					System.out.println(peticion.getObjetoEnviado());
					break;

				default:
					break;
				}
				cliente.close();
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
				if (cliente != null)
					cliente.close();
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

	/**
	 * public static int puerto = 55555; private ServerSocket servidor; private
	 * ArrayList<Socket> clientes; private DataOutputStream salida; private
	 * DataInputStream entrada;
	 * 
	 * public Server() { servidor = null; try { servidor = new ServerSocket(puerto);
	 * clientes = new ArrayList<Socket>();
	 * 
	 * 
	 * } catch (IOException e) { System.out.println("Error: " + e.getMessage()); } }
	 * 
	 * public void esperarAccionesUsuario() { try { entrada.readUTF(); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * @Override public void run() {
	 * 
	 *           System.out.println("Esperando conexiones del cliente..."); try {
	 *           clientes.add(servidor.accept()); System.out.println("Cliente
	 *           conectado"); salida = new
	 *           DataOutputStream(clientes.get(0).getOutputStream()); entrada = new
	 *           DataInputStream(clientes.get(0).getInputStream());
	 *           salida.writeUTF("ENVIADO DESDE EL SERVIDOR");
	 *           System.out.println("Enviado correctamente"); } catch (IOException
	 *           e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 *           }
	 **/

}
