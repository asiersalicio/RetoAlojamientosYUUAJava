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
	int id;

	@Column(name = "municipality")
	String nombre;

	@OneToMany(mappedBy = "tmunicipio")
	private Set<Localizacion> tlocalizacion;

	public Municipio() {
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
