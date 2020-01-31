package com.yuua.reto.xml;

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

	public static void bubbleSort(String[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j+1].equals(ordenar2String(arr[j], arr[j+1], 0))) {
					// swap arr[j+1] and arr[i]
					String temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	public static String ordenar2String(String cadena1, String cadena2, int indice) {
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
				return ordenar2String(cadena1, cadena2, indice+1);
			}
		}
		return cadena1;
	}

}
