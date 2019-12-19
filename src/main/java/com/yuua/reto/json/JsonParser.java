package com.yuua.reto.json;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.yuua.reto.entidades.Alojamiento;

public class JsonParser {

	@SuppressWarnings("unchecked")
	public static String parseAlojamiento(Alojamiento alojamineto)
	{
		JSONObject JSonAlojamiento = new JSONObject();
		JSonAlojamiento.put("id",alojamineto.getId());
		JSonAlojamiento.put("tipo",alojamineto.getTipo());
		JSonAlojamiento.put("nombre", alojamineto.getNombre());
		JSonAlojamiento.put("descripcion",alojamineto.getDescripcion());
		JSonAlojamiento.put("telefono", alojamineto.getTelefono());
		JSonAlojamiento.put("web",alojamineto.getWeb());
		JSonAlojamiento.put("email",alojamineto.getEmail());
		JSonAlojamiento.put("capacidad", alojamineto.getCapacidad());
		
		JSONArray lista = new JSONArray();
		
		lista.add(JSonAlojamiento);
		
		return lista.toJSONString();
		
	}
	
	
	
	
}
