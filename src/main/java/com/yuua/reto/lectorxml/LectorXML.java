package com.yuua.reto.lectorxml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LectorXML {

	public File leerArchivXml(String uri) {
		File archivoXml = new File(uri);
		return archivoXml;
	}
	
	public NodeList sacarNodosXml(File archivoXml) {			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			Document doc;
			try {
				dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.parse(archivoXml);
			} catch (ParserConfigurationException e) {
				return null;
			} catch (SAXException e) {
				return null;
			} catch (IOException e) {
				return null;
			}
			
			doc.getDocumentElement().normalize();
			NodeList listaNodos = doc.getElementsByTagName("row");
			return listaNodos;
			/**
			 * System.out.println("----------------------------");
			 * 
			 * for (int temp = 0; temp < listaNodos.getLength(); temp++) {
			 * 
			 * Node nNode = listaNodos.item(temp);
			 * 
			 * System.out.println("\nCurrent Element :" + nNode.getNodeName());
			 * 
			 * if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 * 
			 * Element eElement = (Element) nNode;
			 * 
			 * System.out.println("Staff id : " + eElement.getAttribute("id"));
			 * System.out.println("First Name : " +
			 * eElement.getElementsByTagName("firstname").item(0).getTextContent());
			 * System.out.println("Last Name : " +
			 * eElement.getElementsByTagName("lastname").item(0).getTextContent());
			 * System.out.println("Nick Name : " +
			 * eElement.getElementsByTagName("nickname").item(0).getTextContent());
			 * System.out.println("Salary : " +
			 * eElement.getElementsByTagName("salary").item(0).getTextContent());
			 * 
			 * } }
			 **/
	}

}
