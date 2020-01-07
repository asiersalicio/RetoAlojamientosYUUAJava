package com.yuua.reto.conexionbd;

import com.yuua.reto.entidades.Alojamiento;
import com.yuua.reto.json.JsonParser;
import com.yuua.reto.net.Server;

public class MainPruebas {

	public static void main(String[] args) {
		
		Alojamiento aloj = new Alojamiento(5, "CAMPING", "Camping", "Hay que tener fe", 94400000, "http://www.camping.com/", "camping@camping.es", 2, null);
		System.out.println(JsonParser.parseAlojamiento(aloj));

		Server servidor = new Server();
		
	}

}
