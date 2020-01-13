package com.yuua.reto.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser {
	public Gson nuevoJsonParser() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson;
	}

}
