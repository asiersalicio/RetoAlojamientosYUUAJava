package com.yuua.reto.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.yuua.reto.entidades.Alojamiento;
import com.yuua.reto.entidades.Localizacion;
import com.yuua.reto.entidades.Municipio;
import com.yuua.reto.entidades.Pais;
import com.yuua.reto.entidades.Territorio;

public class JsonParser {

	@SuppressWarnings("unchecked")
	public String parseAlojamiento(Alojamiento alojam) {

		Localizacion locali = alojam.getLocalizacion();
		Pais pais = locali.getTpais();
		Municipio municipio = locali.getTmunicipio();
		Territorio territorio = locali.getTterritorio();

		JSONObject jsonAlojamiento = new JSONObject();
		JSONObject jsonLocalizacion = new JSONObject();
		JSONObject jsonPais = new JSONObject();
		JSONObject jsonMunicipio = new JSONObject();
		JSONObject jsonTerritorio = new JSONObject();

		jsonPais.put("id", pais.id);
		jsonPais.put("nombre", pais.nombre);

		jsonMunicipio.put("id", municipio.id);
		jsonMunicipio.put("nombre", municipio.nombre);

		jsonTerritorio.put("id", territorio.id);
		jsonTerritorio.put("nombre", territorio.nombre);

		jsonLocalizacion.put("Pais", jsonPais);
		jsonLocalizacion.put("Municipio", jsonMunicipio);
		jsonLocalizacion.put("Territorio", jsonTerritorio);

		jsonAlojamiento.put("id", alojam.getId());
		jsonAlojamiento.put("tipo", alojam.getTipo());
		jsonAlojamiento.put("nombre", alojam.getNombre());
		jsonAlojamiento.put("descripcion", alojam.getDescripcion());
		jsonAlojamiento.put("telefono", alojam.getTelefono());
		jsonAlojamiento.put("web", alojam.getWeb());
		jsonAlojamiento.put("email", alojam.getEmail());
		jsonAlojamiento.put("capacidad", alojam.getCapacidad());
		jsonAlojamiento.put("Localizacion", jsonLocalizacion);

		JSONArray lista = new JSONArray();

		lista.add(jsonAlojamiento);

		return lista.toJSONString();

	}

}
