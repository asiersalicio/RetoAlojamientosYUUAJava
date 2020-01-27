package com.yuua.reto.xml;

public class Utilidades {

	public static String quitarFormateo(String texto)
	{
		texto=texto.replace("&amp;", "&");
		texto=texto.replace("&lt;", "<");
		texto=texto.replace("&gt;", ">");
		texto=texto.replace("<p>", "");
		texto=texto.replace("&eacute ", "é");
		texto=texto.replace("&Eacute ", "É");
		texto=texto.replace("<strong>", "");
		texto=texto.replace("</strong>", "");
		texto=texto.replace("<br>", " ");
		texto=texto.replace("<br/>", " ");
		return texto;
	}
	
}
