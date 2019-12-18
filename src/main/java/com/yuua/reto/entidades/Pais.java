package com.yuua.reto.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tpais")
public class Pais {
	@Id
	@Column(name = "countrycode")
	int id;
	@Column(name = "country")
	String nombre;

	@OneToMany(mappedBy = "tpais")
	private Set<Localizacion> tlocalizacion;

	public Pais() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
