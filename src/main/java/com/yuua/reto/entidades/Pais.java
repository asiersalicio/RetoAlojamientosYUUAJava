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
	public char[] id;
	@Column(name = "country")
	public String nombre;

	@OneToMany(mappedBy = "tpais")
	private Set<Localizacion> tlocalizacion;

	public Pais() {

	}

	public char[] getId() {
		return id;
	}

	public Pais(char[] id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public Pais recrearPais() {
		return new Pais(this.id, this.nombre);
	}

	public void setId(char[] id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
