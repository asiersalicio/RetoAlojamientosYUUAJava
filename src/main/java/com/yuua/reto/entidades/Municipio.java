package com.yuua.reto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tmunicipio")
public class Municipio {
	@Id
	@Column(name ="municipalitycode")
	int id;
	
	@Column(name = "municipality")
	String nombre;
}
