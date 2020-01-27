package com.yuua.reto.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yuua.reto.conexionbd.TransaccionesHibernate;
import com.yuua.reto.entidades.Alojamiento;
import com.yuua.reto.entidades.Localizacion;

import logs.Logger;

public class XMLControler {

	public String url;
	private String filepath;
	private String tipo;
	private int size;
	Document doc;
	private boolean updateRequired = true;
	private TransaccionesHibernate transacciones;

	public XMLControler(String url, String tipo,TransaccionesHibernate transacciones) {
		this.url = url;
		this.tipo = tipo;
		this.transacciones=transacciones;
	}

	public boolean isUpdateRequired() {
		return updateRequired;
	}

	public void setUpdateRequired(boolean updateRequired) {
		this.updateRequired = updateRequired;
	}

	public void downloadNewXML() {
		filepath = System.getProperty("user.dir") + "/" + tipo + url.substring(url.lastIndexOf("/") + 1);
		File archivoAnterior = new File(filepath);
		if (!archivoAnterior.exists()) {
			updateRequired = true;
			sobreecribirXML();
		} else {
			String filepath2 = filepath.split("\\.")[0] + "old.xml";
			archivoAnterior.renameTo(new File(filepath2));
			archivoAnterior = new File(filepath2);
			File archivoNuevo = new File(filepath);
			sobreecribirXML();
			try {
				updateRequired = !FileUtils.contentEquals(archivoNuevo, archivoAnterior);
				archivoAnterior.delete();
			} catch (IOException e) {
				Logger.getInstance().loggear("No se ha podido leer los archivos - 75", this.getClass(), 5);
			}
		}
		this.doc = parseXML();
		this.size = doc.getElementsByTagName("row").getLength();
	}

	private void sobreecribirXML() {
		URLConnection conn;
		try {
			conn = new URL(url).openConnection();
			conn.connect();
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
		} catch (MalformedURLException e) {
			Logger.getInstance().loggear("Url Invalida", this.getClass(), 3);
		}catch (FileNotFoundException e) {
			Logger.getInstance().loggear("Archivo no encontrado", this.getClass(), 5);
		}catch (UnknownServiceException e) {
			Logger.getInstance().loggear("La pagina no puede transmitir la informacion", this.getClass(), 1);
		}catch (SecurityException e) {
			Logger.getInstance().loggear("Permiso de escritura denegado", this.getClass(), 4);
		}catch (IOException e) {
			Logger.getInstance().loggear("No se ha podido leer/abrir el archivo", this.getClass(), 5);
		}
	}

	private Node extractNodeFromXML(int idlist) {
		NodeList nList = doc.getElementsByTagName("row");
		Node nNode = nList.item(idlist);
		return nNode;
	}

	public int getSize() {
		return this.size;
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
		String nombre = "", descripcion = "", direccion = "", web = "", email = "", tipo = "", paisString = "", municipioString = "", territorioString = "", codigoPostal = "";
		int telefono = -1, capacidad = -1;
		double latitud, longitud;

		Alojamiento aloj = null;
		Node xmlNode = extractNodeFromXML(id);

		if (xmlNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) xmlNode;
			nombre = obtenerElement(eElement, "documentname", 0);
			tipo = obtenerElement(eElement, "lodgingtype", 0);
			descripcion = obtenerElement(eElement, "turismdescription", 1);
			direccion = obtenerElement(eElement, "address", 0);
			try {
				telefono = Integer.parseInt(obtenerElement(eElement, "phone", 0).replace(" ", ""));
			} catch (NumberFormatException | NullPointerException f) {
				telefono = -1;
			}
			web = obtenerElement(eElement, "web", 0);
			email = obtenerElement(eElement, "tourismemail", 0);
			capacidad = Integer.parseInt(obtenerElement(eElement, "capacity", 0));
			paisString = obtenerElement(eElement, "country", 0);
			municipioString = obtenerElement(eElement, "municipality", 0);
			territorioString = obtenerElement(eElement, "territory", 0);
			codigoPostal = obtenerElement(eElement, "postalcode", 0);
			try {
				latitud = Double.valueOf(obtenerElement(eElement, "latwgs84", 0));
			} catch (NumberFormatException | NullPointerException e) {
				latitud = 0;
			}
			try {
				longitud = Double.valueOf(obtenerElement(eElement, "lonwgs84", 0));
			} catch (NumberFormatException | NullPointerException e) {
				longitud = 0;
			}		
			if(paisString==null || municipioString == null || territorioString == null) {
				return null;
			}
			if(latitud == 0 || longitud == 0) {
				return null;
			}
			Localizacion loc = new Localizacion(paisString, municipioString, territorioString, codigoPostal, direccion, latitud, longitud);
			aloj = new Alojamiento(tipo, nombre, descripcion, telefono, web, email, capacidad, loc);
		}
		return aloj;
	}

//	public ArrayList<Object> buscarElementos(String nombreCodigo, String nombreCampo, Class<?> tipo) {
//		ArrayList<Object> elementos = new ArrayList<Object>();
//		Set<String> nombres = new HashSet<String>();
//		Set<String> codigos = new HashSet<String>();
//		for (int i = 0; i < this.size; i++) {
//			Node nodo = extractNodeFromXML(i);
//			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
//				String nombre = obtenerElement((Element) nodo, nombreCampo, 0);
//				if (nombre.contains(" ")) {
//					String[] split = nombre.split(" ");
//					if (split[0].equals(split[1])) {
//						nombre = split[0];
//					}
//				}
//				nombres.add(nombre);
//				String codigo = obtenerElement((Element) nodo, nombreCodigo, 0);
//				if (codigo.contains(" ")) {
//					codigo = codigo.substring(0, codigo.indexOf(" "));
//				}
//				codigos.add(codigo);
//			}
//		}
//		Iterator<String> it1 = codigos.iterator();
//		Iterator<String> it2 = nombres.iterator();
//
//		while (it1.hasNext() && it2.hasNext()) {
//			if (tipo == Pais.class) {
//				elementos.add(new Pais(it1.next().toCharArray(), it2.next()));
//			} else if (tipo == Territorio.class) {
//				elementos.add(new Territorio(it1.next().toCharArray(), it2.next()));
//			} else if (tipo == Municipio.class) {
//				elementos.add(new Municipio(it1.next().toCharArray(), it2.next()));
//			} else {
//				return null;
//			}
//		}
//		return elementos;
//	}

	private String obtenerElement(Element eElement, String tagName, int itemIndex) {
		Node node = eElement.getElementsByTagName(tagName).item(itemIndex);
		if (node != null) {
			return Utilidades.quitarFormateo(node.getTextContent());
		}
		return null;
	}

}
