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

}
