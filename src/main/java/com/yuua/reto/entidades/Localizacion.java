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

}
