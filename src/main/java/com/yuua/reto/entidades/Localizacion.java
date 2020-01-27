package com.yuua.reto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tlocalizacion")
public class Localizacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idLocalizacion")
	int id;
	@Column(name = "country")
	String tpais;
	@Column(name = "municipality")
	String tmunicipio;
	@Column(name = "territory")
	String tterritorio;
	@Column(name = "postalcode")
	String codigoPostal;
	@Column(name = "address")
	String direccion;
	@Column(name = "latwgs84")
	Double latitud;
	@Column(name = "lonwgs84")
	Double longitud;

	public Localizacion() {

	}

	public Localizacion(String tpais, String tmunicipio, String tterritorio, String codigoPostal, String direccion, Double latitud, Double longitud) {
		this.tpais = tpais;
		this.tmunicipio = tmunicipio;
		this.tterritorio = tterritorio;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Localizacion(String codigoPostal, String direccion, Double latitud, Double longitud) {
		super();
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTpais() {
		return tpais;
	}

	public void setTpais(String tpais) {
		this.tpais = tpais;
	}

	public String getTmunicipio() {
		return tmunicipio;
	}

	public void setTmunicipio(String tmunicipio) {
		this.tmunicipio = tmunicipio;
	}

	public String getTterritorio() {
		return tterritorio;
	}

	public void setTterritorio(String tterritorio) {
		this.tterritorio = tterritorio;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

}
