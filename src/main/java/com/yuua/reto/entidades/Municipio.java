package com.yuua.reto.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tmunicipio")
public class Municipio {
	@Id
	@Column(name = "municipalitycode")
	public char[] id;

	@Column(name = "municipality")
	public String nombre;

	@OneToMany(mappedBy = "tmunicipio")
	private Set<Localizacion> tlocalizacion;

	public Municipio() {
	}

	public Municipio(char[] id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Municipio recrearMunicipio() {
		return new Municipio(this.id, this.nombre);
	}

	public char[] getId() {
		return id;
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
