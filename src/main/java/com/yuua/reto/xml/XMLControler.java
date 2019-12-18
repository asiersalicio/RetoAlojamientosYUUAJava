package com.yuua.reto.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yuua.reto.entidades.Alojamiento;
import com.yuua.reto.entidades.Localizacion;
import com.yuua.reto.entidades.Municipio;
import com.yuua.reto.entidades.Pais;

public class XMLControler {

	public String url;
	private String filepath;
	private String tipo;

	public XMLControler(String url, String tipo) {
		this.url = url;
		this.tipo= tipo;
	}

	public void downloadNewXML() {
		try {
			filepath=System.getProperty("user.dir") + "/" + tipo + url.substring(url.lastIndexOf("/")+1);

			URLConnection conn = new URL(url).openConnection();

			conn.connect();

			System.out.println(conn.getContentType());

			InputStream is = conn.getInputStream();
			FileOutputStream fos = new FileOutputStream(new File(filepath));

			byte[] array = new byte[1000];
			int leido = is.read(array);
			while (leido > 0) {
				fos.write(array, 0, leido);
				leido = is.read(array);
			}

			is.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Node extractNodeFromXML(int idlist) {
		Document doc = parseXML();
		NodeList nList = doc.getElementsByTagName("row");
		Node nNode = nList.item(idlist);
		return nNode;
	}
	
	public int getSize()
	{
		Document doc = parseXML();
		NodeList nList = doc.getElementsByTagName("row");
		return nList.getLength();
	}

	private Document parseXML() {
		File fXmlFile = new File(filepath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		doc.getDocumentElement().normalize();
		return doc;
	}

	public Alojamiento toAlojamientoById(int id) {
		String nombre = "", descripcion = "", direccion = "", web = "", email = "", tipo = "", pais = "", municipio = "", territorio = "", codigoPostal = "", marca = "";
		int telefono = -1, capacidad = -1;
		double latitud, longitud;
		
		Alojamiento aloj = null;
		id--;
		Node xmlNode=extractNodeFromXML(id);
		
		if (xmlNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) xmlNode;

			nombre = obtenerElement(eElement, "documentname", 0);
			tipo = obtenerElement(eElement, "lodgingtype", 0);
			descripcion = obtenerElement(eElement, "turismdescription", 1);
			direccion = obtenerElement(eElement, "address", 0);
			telefono = Integer.parseInt(obtenerElement(eElement, "phone", 0).replace(" ", ""));
			web = obtenerElement(eElement, "web", 0);
			email = obtenerElement(eElement, "tourismemail", 0);
			capacidad = Integer.parseInt(obtenerElement(eElement, "capacity", 0));
			
			
			
			System.out.println("Nombre: " + nombre);
			System.out.println("Tipo: " + tipo);
			System.out.println("Descripcion: " + descripcion);
			System.out.println("Direccion: " + direccion);
			System.out.println("Telefono: " + telefono);
			System.out.println("Web: " + web);
			System.out.println("Email: " + email);
			System.out.println("Capacidad: " + capacidad);
			
			
			//Localizacion loc = new Localizacion(id, null, municipio, territorio, codigoPostal, direccion, marca, latitud, longitud);
			aloj = new Alojamiento(id, tipo, nombre, descripcion, telefono, web, email, capacidad, null);
			
			
		}
		return aloj;
	}
	
	private String obtenerElement(Element eElement, String tagName, int itemIndex)
	{
		Node node = eElement.getElementsByTagName(tagName).item(itemIndex);
		if(node!=null)
		{
			return Utilidades.quitarFormateo(node.getTextContent());
		}
		return "";
	}
	
}
