package com.yuua.reto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tpais")
public class Pais {
	@Id
	@Column(name="countrycode")
	int id;
	@Column(name = "country")
	String nombre;
}
