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

public class XMLControler {

	private Document doc;
	public String url;
	private String filepath;

	public XMLControler(String url) {
		this.url = url;
	}

	public void downloadNewXML() {
		try {
			filepath=System.getProperty("user.dir") + url.substring(url.lastIndexOf("/"));

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

		doc = parseXML();
	}

	private Node extractNodeFromXML(int idlist) {
		Document doc = parseXML();
		NodeList nList = doc.getElementsByTagName("row");
		Node nNode = nList.item(idlist);
		return nNode;
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
		
		Node xmlNode=extractNodeFromXML(id);
		
		if (xmlNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) xmlNode;

			String nombre = eElement.getElementsByTagName("documentname").item(0).getTextContent();
			String descripcion = eElement.getElementsByTagName("turismdescription").item(1).getTextContent();
			String direccion = eElement.getElementsByTagName("address").item(0).getTextContent();
			System.out.println("Nombre: " + nombre);
			System.out.println("Descripcion: " + descripcion);
			System.out.println("Direccion: " + direccion);
		}
		return null;
	}
}
