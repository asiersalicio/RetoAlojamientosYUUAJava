package com.yuua.reto.conexionbd;

import com.yuua.reto.entidades.Alojamiento;
import com.yuua.reto.json.JsonParser;

public class MainPruebas {

	public static void main(String[] args) {
		Alojamiento aloj = new Alojamiento(5, "CAMPING", "Camping el calvo de la loteria", "Hay que tener fe", 94400000, "http://www.calvoloteria.com/", "calvo@loterias.es", 2);
		System.out.println(JsonParser.parseAlojamiento(aloj));

	}

}
