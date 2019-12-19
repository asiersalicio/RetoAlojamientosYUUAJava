package com.yuua.reto.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{

	public static int puerto = 55555;
	private ServerSocket servidor;
	private ArrayList<Socket> clientes;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	
	public Server() 
	{
		servidor = null;
		try {
			servidor = new ServerSocket(puerto);
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
			salida = new ObjectOutputStream(clientes.get(0).getOutputStream());
			entrada = new ObjectInputStream(clientes.get(0).getInputStream());
			while (true)
			{
				String orden=entrada.readUTF();
				if(orden.equals("0"))
				{
					System.out.println("Recibido");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
