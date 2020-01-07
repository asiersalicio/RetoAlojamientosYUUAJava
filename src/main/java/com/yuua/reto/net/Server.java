package com.yuua.reto.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{

	public static int puerto = 55555;
	private ServerSocket servidor;
	private ArrayList<Socket> clientes;
	private DataOutputStream salida;
	private DataInputStream entrada;
	
	public Server() 
	{
		servidor = null;
		try {
			servidor = new ServerSocket(puerto);
			clientes = new ArrayList<Socket>();
			start();
			
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public void esperarAccionesUsuario()
	{
		try {
			entrada.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		System.out.println("Esperando conexiones del cliente...");
		try {
			clientes.add(servidor.accept());
			System.out.println("Cliente conectado");
			salida = new DataOutputStream(clientes.get(0).getOutputStream());
			entrada = new DataInputStream(clientes.get(0).getInputStream());
			salida.writeUTF("ENVIADO DESDE EL SERVIDOR");
			System.out.println("Enviado correctamente");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
