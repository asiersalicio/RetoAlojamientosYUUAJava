package com.yuua.reto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tlocalizacion")
public class Localizacion {
	@Id
	@Column(name = "idLocalizacion")
	int id;
	@ManyToOne
	@JoinColumn(name = "countrycode")
	Pais tpais;
	@ManyToOne
	@JoinColumn(name = "municipalitycode")
	Municipio tmunicipio;
	@ManyToOne
	@JoinColumn(name = "territorycode")
	Territorio tterritorio;

	@Column(name = "postalcode")
	String codigoPostal;
	@Column(name = "address")
	String direccion;
	@Column(name = "marks")
	String marca;
	@Column(name = "latwgs84")
	Double latitud;
	@Column(name = "lonwgs84")
	Double longitud;

	public Localizacion() {

	}

	public Localizacion(int id, Pais tpais, Municipio tmunicipio, Territorio tterritorio, String codigoPostal, String direccion, String marca, Double latitud, Double longitud) {
		super();
		this.id = id;
		this.tpais = tpais;
		this.tmunicipio = tmunicipio;
		this.tterritorio = tterritorio;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.marca = marca;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pais getTpais() {
		return tpais;
	}

	public void setTpais(Pais tpais) {
		this.tpais = tpais;
	}

	public Municipio getTmunicipio() {
		return tmunicipio;
	}

	public void setTmunicipio(Municipio tmunicipio) {
		this.tmunicipio = tmunicipio;
	}

	public Territorio getTterritorio() {
		return tterritorio;
	}

	public void setTterritorio(Territorio tterritorio) {
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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
