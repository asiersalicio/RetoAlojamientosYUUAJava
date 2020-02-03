package com.yuua.reto.xml;

import java.util.ArrayList;
import java.util.Collections;

import com.yuua.reto.entidades.Alojamiento;

public class Utilidades {

	public static String quitarFormateo(String texto) {
		texto = texto.replace("&amp;", "&");
		texto = texto.replace("&lt;", "<");
		texto = texto.replace("&gt;", ">");
		texto = texto.replace("<p>", "");
		texto = texto.replace("&aacute ", "á");
		texto = texto.replace("&Aacute ", "Á");
		texto = texto.replace("&eacute ", "é");
		texto = texto.replace("&Eacute ", "É");
		texto = texto.replace("&uacute ", "ú");
		texto = texto.replace("&Uacute ", "Ú");
		texto = texto.replace("&oacute ", "ó");
		texto = texto.replace("&Oacute ", "Ó");
		texto = texto.replace("&iacute ", "í");
		texto = texto.replace("&Iacute ", "Í");
		texto = texto.replace("&ntilde ", "ñ");
		texto = texto.replace("&Ntilde ", "Ñ");
		texto = texto.replace("&#39", "");
		texto = texto.replace("<br>", " ");
		texto = texto.replace("<br/>", " ");
		texto = texto.replace("&quot;", "");
		texto = texto.replace("\u00a0", " ");
		texto = texto.replace("&nbsp", " ");
		return texto;
	}

	public static String quitarTags(String texto) {
		while (texto.contains("<")) {
			String auxPre = texto.substring(0, texto.indexOf("<"));
			String auxPos = texto.substring(texto.indexOf(">") + 1);
			texto = auxPre + auxPos;
		}
		return texto;
	}
	
	public static final int ASCENDENTE = 20;
	public static final int DESCENDENTE = 10;

	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					// swap arr[j+1] and arr[i]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	public static void bubbleSort(Double[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					// swap arr[j+1] and arr[i]
					double temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
	
//	public void ordenarAlojamientosPorPrecio(ArrayList<Alojamiento> alojs,int forma) {
//		for (int i = 0; i < alojs.size() - 1; i++) {
//			for (int j = 0; j < alojs.size() - i - 1; j++) {
//				if (alojs.get(j+1).getPrecio())) {
//					Alojamiento temp = alojs.get(j+1);				
//					alojs.set(j+1,alojs.get(j));
//					alojs.set(j, temp);
//				}
//			}
//		}
//		if(forma==ASCENDENTE) {
//			Collections.reverse(alojs);
//		}
//	}
	
	
	public void ordenarAlojamientosPorNombre(ArrayList<Alojamiento> alojs,int forma) {
		for (int i = 0; i < alojs.size() - 1; i++) {
			for (int j = 0; j < alojs.size() - i - 1; j++) {
				if (alojs.get(j+1).equals(compararStringsRec(alojs.get(j).getNombre(), alojs.get(j+1).getNombre(), 0))) {
					Alojamiento temp = alojs.get(j+1);				
					alojs.set(j+1,alojs.get(j));
					alojs.set(j, temp);
				}
			}
		}
		if(forma==ASCENDENTE) {
			Collections.reverse(alojs);
		}
	}

	public static void OrdenarStrings(ArrayList<String> arr,int forma) {
		for (int i = 0; i < arr.size() - 1; i++) {
			for (int j = 0; j < arr.size() - i - 1; j++) {
				if (arr.get(j+1).equals(compararStringsRec(arr.get(j), arr.get(j+1), 0))) {
					String temp = arr.get(j+1);				
					arr.set(j+1,arr.get(j));
					arr.set(j, temp);
				}
			}
		}
		if(forma==ASCENDENTE) {
			Collections.reverse(arr);
		}
	}
	
	

	public static String compararStringsRec(String cadena1, String cadena2, int indice) {
		if (cadena1.length() <= indice) {
			if (cadena2.length() <= indice) {
				return cadena1;
			} else {
				return cadena2;
			}
		} else if (cadena2.length() <= indice) {
			return null;
		} else {
			if (cadena1.charAt(indice) > cadena2.charAt(indice)) {
				return cadena1;
			} else if (cadena1.charAt(indice) < cadena2.charAt(indice)) {
				return cadena2;
			}else if (cadena1.charAt(indice) == cadena2.charAt(indice)){
				return compararStringsRec(cadena1, cadena2, indice+1);
			}
		}
		return cadena1;
	}

}
