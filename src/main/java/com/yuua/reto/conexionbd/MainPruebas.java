package com.yuua.reto.conexionbd;

import com.yuua.reto.net.Server;

public class MainPruebas {

	public static void main(String[] args) {

		Server servidor = new Server();
		Thread hiloserver = new Thread(servidor);
		hiloserver.start();

	}

}
