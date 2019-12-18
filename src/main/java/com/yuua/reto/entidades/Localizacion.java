package com.yuua.reto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tlocalizacion")
public class Localizacion {
	@Id
	int id;
	Pais pais;
	Municipio municipio;
	Territorio territorio;
	
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
}
